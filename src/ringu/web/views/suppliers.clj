(ns ringu.web.views.suppliers
  (:require [hiccup.page :as hp]))

(def inline-style "")

(defn table-row [r]
  [:tr
   [:td (:id r)]
   [:td (:name r)]])

(defn index-page [req data]
  (hp/html5
   [:html
    [:head
     [:title "Hello"]
     (hp/include-css "https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css")
     [:style inline-style]]

    [:body

     [:main {:class "container"}
      1[:div (str req)]2
      [:div (:query-string req)]
      [:div (str (get (:query-params req) "search")  )]
      [:div (str (:params req))]
      [:h1 "the index-page"]
      [:h3 "the index-page"]
      [:div  {:class "row"}
       [:div {:class "col-md-12"}
        [:form {:class "form form-inline"} 
         [:label {:for "search"} "Αναζήτηση:&nbsp;&nbsp;"]
         [:input {:id "search" :class "form-control" :name "search"}]
         [:input {:class "btn btn-info" :type "submit"}]
         ]
        [:table {:class "table"}
         [:thead [:tr [:th "Id"] [:th "Όνομα"]]]
         [:tbody (map  table-row data)]]]]]]]))