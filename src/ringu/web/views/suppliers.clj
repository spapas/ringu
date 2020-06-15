(ns ringu.web.views.suppliers
  (:require [ringu.web.views.helpers :as helpers]))

(def inline-style "")

(defn table-row [r]
  [:tr
   [:td (:id r)]
   [:td (:name r)]])

(defn index-page [req data]
   (helpers/layout "Προμηθευτές"
                    [:div 
                    [:div (str (get (:query-params req) "search"))]
                    [:div (str (:params req))]
                    [:div  {:class "row"}
                     [:div {:class "col-md-12"}
                      [:form {:class "form form-inline"}
                       [:label {:for "search"} "Αναζήτηση:&nbsp;&nbsp;"]
                       [:input {:id "search" :class "form-control" :name "search"}]
                       [:input {:class "button info" :value "Αναζήτηση" :type "submit"}]]
                      [:table {:class "table"}
                       [:thead [:tr [:th "Id"] [:th "Όνομα"]]]
                       [:tbody (map  table-row data)]]]]]))