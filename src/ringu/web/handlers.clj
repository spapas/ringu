(ns ringu.web.handlers
  (:require [ring.util.response :refer [response content-type]]))


(defn html-resp [f]
  (fn [req]
    (let [r (f req)]
      (if (= (type r) java.lang.String)
         (content-type  (response r) "text/html; charset=utf-8")
        r))))

; OR
;(defn html-resp [f]
;  (fn [req]
;    (-> req f response (content-type "text/html; charset=utf-8"))))