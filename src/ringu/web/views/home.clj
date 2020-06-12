(ns ringu.web.views.home
  (:require [hiccup.page :as hp]))

(def inline-style "
                   h1 { margin-top: 10px; }
                   body {font-family: 'Comic Sans MS' !important;}
                   ")

(defn index-page [data]

  (hp/html5
   [:html
    [:head
     [:title "Hello"]
     (hp/include-css "https://unpkg.com/papercss/dist/paper.min.css")
     [:style inline-style]]

    [:body
     [:div  {:class "row"}
      [:div {:class "md-12 col"}
       [:div {:class "paper"}
        [:h1 "the index-page"]
        [:h3 "the index-page"]

        [:div (map  #(vector :li (:name %)) data)]]]]]]))