(ns ringu.web.handlers
  (:require [ring.util.response :refer [response content-type]]
            [ringu.web.views.home :as home]
            [ringu.web.views.suppliers :as suppliers]))


(defn home [req]
  (content-type
   (response (home/index-page req))
   "text/html; charset=utf-8"))

(defn suppliers [req]
  (content-type
   (response (suppliers/index-page req))
   "text/html; charset=utf-8"))

(defn html-resp [f]
  (fn [req]
    (content-type
     (response (f req))
     "text/html; charset=utf-8")))

; OR
;(defn html-resp2 [f]
;  (fn [req]
;    (-> req f response (content-type "text/html; charset=utf-8"))))