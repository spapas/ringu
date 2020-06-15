(ns ringu.web.handlers
  (:require [ring.util.response :refer [response content-type]])
  (:require [ringu.db.suppliers :as su])
  (:require [ringu.web.views.home :as home])
  (:require [ringu.web.views.suppliers :as suppliers])
  (:require [ringu.db.core :as db]))


(defn home [req]
  (content-type
   (response (home/index-page req ))
   "text/html; charset=utf-8"))

(defn suppliers [req]
  (content-type
   (response (suppliers/index-page req (su/all-suppliers (db/db-connection))))
   ;(response (au/all-authorities (db/db-connection)))
   "text/html; charset=utf-8"))