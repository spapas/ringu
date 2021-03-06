(ns ringu.web.views.suppliers
  (:require [ringu.web.views.helpers :as helpers]
            [ringu.db.suppliers :as su]
            [struct.core :as st]
            [ring.util.response :refer [redirect]]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [ringu.db.core :as db]))

(def inline-style "")

(defn table-row [r]
  [:tr
   [:td (:id r)]
   [:td (:name r)]])

(defn index-page [req]
  (let
   [search (get (:params req) :search)
    page (helpers/get-page req)
    suppliers (su/search-by-name-like (db/db-connection) search page)
    total-pages (su/total-pages-by-name-like (db/db-connection) search)]
    (helpers/layout req
                    "Προμηθευτές"
                    [:div "Προμηθευτές" [:a {:href (helpers/get-path :suppliers-add) :title "Προσθήκη" :class "mx-8 button primary"} [:span {:class "mif-plus"}]]]
                    [:div
                     [:div  {:class "row"}
                      [:div {:class "col-md-12"}
                       [:form {:class "inline-form"}
                        [:div {:class "form-group"}
                         [:label {:for "search"} "Αναζήτηση:&nbsp;&nbsp;"]
                         [:input {:id "search" :class "form-control" :name "search" :value search}]]
                        [:input {:class "button info" :value "Αναζήτηση" :type "submit"}]]
                       [:table {:class "table"}
                        [:thead [:tr [:th "Id"] [:th "Όνομα"]]]
                        [:tbody (map  table-row suppliers)]]
                       (helpers/pagination req total-pages)]]])))

(defn- form [data errors]
  [:form {:method "POST" :class "form form-inline" :novalidate ""}
   (anti-forgery-field)
   [:div {:class "form-group"}
    [:label {:for "name"} "Όνομα:&nbsp;&nbsp;"]
    [:input {:id "name"
             :class (str "form-control " (when (:name errors) "invalid"))
             :name "name"
             :type "text"
             :value (:name data)}]
    [:div {:class "invalid_feedback"} (:name errors)]]
   [:div {:class "form-group"}
    [:input {:class "button primary" :value "Προσθήκη" :type "submit"}]
    [:a {:class "button info" :href (helpers/get-path :suppliers)} "Επιστροφή"]]])

(defn add
  ([req] (add req {} {}))
  ([req data errors]
   (helpers/layout req
                   "Προσθήκη προμηθευτή" (form data errors))))

(def supplier
  {:name [[st/min-count 4 :message "Πρέπει να έχει τουλάχιστον 4 χαρακτήρες"]
          [st/required :message "Το πεδίο είναι απαραίτητο"]
          st/string]})

(defn insert-supplier [req data]
  (try
    (let [id (su/insert-get-id (db/db-connection) data)]
      (helpers/put-flash (redirect (helpers/get-path :suppliers)) (str "Επιτυχής προσθήκη! Κωδ: " id ".")))
    (catch java.sql.SQLException _e (add req data {:name "Υπάρχει ήδη!"}))))

(defn add-post [req]
  (let [[errors data] (-> (:params req) (st/validate supplier {:strip true}))]
    (if (nil? errors) (insert-supplier req data) (add req data errors))))

(comment
  (-> {:year 1999 :name "1"} (st/validate supplier {:strip true})))