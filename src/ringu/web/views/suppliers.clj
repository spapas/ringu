(ns ringu.web.views.suppliers
  (:require [ringu.web.views.helpers :as helpers]
            [ringu.db.suppliers :as su]
            [struct.core :as st]
            [ringu.db.core :as db]))

(def inline-style "")

(defn table-row [r]
  [:tr
   [:td (:id r)]
   [:td (:name r)]])

(defn index-page [req]
  (let [suppliers (su/all-suppliers (db/db-connection))]
    (helpers/layout
     [:div "Προμηθευτές" [:a {:href (helpers/get-path :suppliers-add) :title "Προσθήκη" :class "mx-8 button primary"} [:span {:class "mif-plus"}]]]
     [:div
      [:div (str (get (:query-params req) "search"))]
      [:div (str (:params req))]
      [:div  {:class "row"}
       [:div {:class "col-md-12"}
        [:form {:class "inline-form"}
         [:div {:class "form-group"}
          [:label {:for "search"} "Αναζήτηση:&nbsp;&nbsp;"]
          [:input {:id "search" :class "form-control" :name "search"}]]
         [:input {:class "button info" :value "Αναζήτηση" :type "submit"}]]
        [:table {:class "table"}
         [:thead [:tr [:th "Id"] [:th "Όνομα"]]]
         [:tbody (map  table-row suppliers)]]]]])))

(defn- form [data errors]
  [:form {:method "POST" :class "form form-inline" :novalidate ""}
   [:div {:class "form-group"}
    [:label {:for "name"} "Όνομα:&nbsp;&nbsp;"]
    [:input {:id "name"
             :class (str "form-control " (when (:name errors) "invalid"))
             :name "name"
             :value (:name data)}]
    [:div {:class "invalid_feedback"} (:name errors)]]
   [:div {:class "form-group"}
    [:input {:class "button primary" :value "Προσθήκη" :type "submit"}]
    [:a {:class "button info" :href (helpers/get-path :suppliers)} "Επιστροφή"]]])

(defn add
  ([req] (add req {} {}))
  ([_req data errors]
   (helpers/layout
    "Προσθήκη προμηθευτή" (form data errors))))

(def supplier
  {:name [[st/min-count 4 :message "Πρέπει να έχει τουλάχιστον 4 χαρακτήρες"]
          [st/required :message "Το πεδίο είναι απαραίτητο"]
          st/string]})

(defn add-post [req]
  (let [[errors data] (-> (:params req) (st/validate supplier {:strip true}))]
    (if (nil? errors) "OK" (add req data errors))))

(comment
  (-> {:year 1999 :name "1"} (st/validate supplier {:strip true})))