(ns ringu.web.views.helpers
  (:require [reitit.core :as r]
            [hiccup.page :as hp]))

(defn get-path 
  ([name] (get-path name {}))
  ([name params]
  (:path (r/match-by-name (deref (resolve 'ringu.web.router/router)) name params))))

(def inline-style "")

(defn layout [title content]
  (hp/html5
   [:html
    [:head
     [:meta {:charset "utf-8"}]
     [:meta {:name "viewport"
             :content "width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"}]

     [:title title]
     (hp/include-css "https://cdn.metroui.org.ua/v4/css/metro-all.min.css")
     [:style inline-style]]

    [:body
     [:div {:class "container"}
      [:h1 {:class "h1"} title]
     content
      ]
     [:script {:src "https://cdn.metroui.org.ua/v4/js/metro.min.js"}]
     ]]))