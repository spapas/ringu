(ns ringu.web.handlers
  (:require [ring.util.response :refer [response content-type]])
  (:require [ringu.db.authorities :as au])
  (:require [ringu.web.views.home :as home])
  (:require [ringu.db.core :as db]))


(defn home [_]
  (content-type
   (response (home/index-page (au/all-authorities (db/db-connection))))
   ;(response (au/all-authorities (db/db-connection)))
   "text/html; charset=utf-8"))
