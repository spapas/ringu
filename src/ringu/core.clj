(ns ringu.core
  (:require [reitit.ring :as ring]
            [ringu.handlers :as handlers]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.reload :refer [wrap-reload]]))

(defn handler [_]
  {:status 200, :body "ok!!zzzz"})


(def app
  (ring/ring-handler
   (ring/router
    [["/" handlers/home]
     ["/pang" handler]
     ["/pong" handler]
     ["/ping" handler]])
   (ring/create-default-handler)))


(def reloadable-app (wrap-reload #'app))

(defonce server (run-jetty reloadable-app
                           {:port 3000 :join? false}))
;(defonce server (run-jetty #'reloadable-app {:port 3000 :join? false}))
;(def start-jetty(run-jetty reloadable-app
                     ;{:port  3000 :join? false}))