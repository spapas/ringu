(ns ringu.web.views.helpers
  (:require [reitit.core :as r]
            [clojure.data.json :as j]
            [ring.util.codec :as codec]
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

(defn get-page [req]
  (Integer/parseInt (get (:params req) :page "1")))

(defn app-bar []
  [:nav {:class "bg-lightGray mt-0 py-1" :data-role "appbar", :data-expand-point "md"}
   [:a {:href (get-path :home), :class "brand no-hover"}
    [:span {:class "px-2 border bd-dark border-radius"} "Αρχή"]]
   [:ul {:class "app-bar-menu"}
    [:li
     [:a {:href (get-path :suppliers)} "Προμηθευτές"]]
    [:li
     [:a {:href (get-path :suppliers)} "Προϊόντα"]]
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

(defn pagination [req total-pages]
  (let [params (:params req)
        page (get-page req)
        path (get req :uri)]
    [:ul {:class "pagination no-gap"}
     [:li {:class (str "page-item " (if (<= page 1) "disabled" "service"))}
      [:a {:href (str path "?" (codec/form-encode (assoc params :page (dec page))))
           :class "page-link"} [:span {:class "mif-arrow-left"}]]]
     [:li {:class "page-item active"}
      [:a {:class "page-link"} (str page)]]
     [:li {:class (str "page-item " (if (>= page total-pages) "disabled" "service"))}
      [:a {:href (str path "?" (codec/form-encode (assoc params :page (inc page))))
           :class "page-link"} [:span {:class "mif-arrow-right"}]]]]))

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

