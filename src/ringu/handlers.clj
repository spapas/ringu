(ns ringu.handlers
  (:require [ring.util.response :refer [response content-type]])
  (:require [ringu.db.authorities :as au])
  (:require [ringu.db.core :as db]))


(defn home [_]
  (content-type
   (response (au/all-authorities (db/db-connection)))
   "text/html; charset=utf-8"))
