(ns ringu.core
  (:require [reitit.ring :as ring]
            [ring.adapter.jetty :refer [run-jetty]]
            [ringu.web.router :refer[router]]
            [ring.middleware.reload :refer [wrap-reload]]))


(def app
  (ring/ring-handler
   router
   (ring/create-default-handler)))

(def reloadable-app (wrap-reload #'app))

(defonce server (run-jetty reloadable-app{:port 3000 :join? false}))
