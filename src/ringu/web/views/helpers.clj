(ns ringu.web.views.helpers
  (:require [reitit.core :as r])
  (:require [ringu.core :refer [router]]))

(defn get-path [name params]
  (:path (r/match-by-name router name params)))