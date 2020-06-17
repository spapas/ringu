(ns ringu.web.views.helpers
  (:require [reitit.core :as r]
            [clojure.data.json :as j]
            [hiccup.page :as hp]))

(defn get-path
  ([name] (get-path name {}))
  ([name params]
   (:path (r/match-by-name (deref (resolve 'ringu.web.router/router)) name params))))

(def inline-style "")

(defn put-flash [req message]
  (assoc req :flash (j/write-str message)))

(defn get-flash [req]
  (let [f (:flash req)]
    (when (not (nil? f)) (j/read-str f))))

(defn app-bar []
  [:nav {:class "bg-lightGray mt-0 py-1" :data-role "appbar", :data-expand-point "md"}
   [:a {:href (get-path :home), :class "brand no-hover"}
    [:span {:class "px-2 border bd-dark border-radius"} "Αρχή"]]
   [:ul {:class "app-bar-menu"}
    [:li
     [:a {:href (get-path :suppliers)} "Προμηθευτές"]]
    [:li
     [:a {:href "#", :class "dropdown-toggle"} "Products"]
     [:ul {:class "d-menu", :data-role "dropdown"}
      [:li
       [:a {:href "#"} "Windows 10"]]
      [:li
       [:a {:href "#"} "Office 365"]]
      [:li {:class "divider bg-lightGray"}]
      [:li
       [:a {:href "#"} "Skype"]]]]
    [:li
     [:a {:href "#"} "Contacts"]]]])

(defn layout
  ([req title content] (layout req title title content))
  ([req title_header title content]
   (hp/html5
    [:html
     [:head
      [:meta {:charset "utf-8"}]
      [:meta {:name "viewport"
              :content "width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"}]

      [:title title_header]
      (hp/include-css "https://cdn.metroui.org.ua/v4/css/metro-all.min.css")
      [:style inline-style]]

     [:body
      [:div {:class "container"}
       (app-bar)
       [:h1 {:class "h1 mt-18"} title]
       (let [m (get-flash req)] (when-not (nil? m) [:div {:class "remark primary"} m]))

       content]
      [:script {:src "https://cdn.metroui.org.ua/v4/js/metro.min.js"}]]])))

