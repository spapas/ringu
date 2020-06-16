(ns ringu.web.handlers
  (:require [ring.util.response :refer [response content-type]]))


(defn html-resp [f]
  (fn [req]
    (content-type
     (response (f req))
     "text/html; charset=utf-8")))

; OR
;(defn html-resp2 [f]
;  (fn [req]
;    (-> req f response (content-type "text/html; charset=utf-8"))))