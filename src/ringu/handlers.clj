(ns ringu.handlers
  (:require [ring.util.response :refer [response]])
  (:require [ringu.db.authorities :as au])
  (:require [ringu.db.core :as db]))


(defn home [_]
  (response (au/all-authorities (db/db-connection))))
