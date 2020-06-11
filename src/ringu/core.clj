(ns ringu.core
  (:require [reitit.ring :as ring]
            [ringu.web.exception :as exception]
            [ringu.web.handlers :as handlers]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.reload :refer [wrap-reload]]))

(defn handler [_]
  {:status 200, :body "ok!!zzzz"})

(defn handler_id [{{id :id } :path-params}]
  {:status 200, :body (str "zz " id)})

(def app
  (ring/ring-handler
   (ring/router
    [["/" handlers/home]
     ["/fail" (fn [_] (throw (ex-info "fail" {:type ::failure})))]
     ;["/fail" (fn [_] (throw (Exception. "fail")))]
     ["/pang/:id" handler_id]
     ["/pong" handler]
     ["/ping" handler]
     ]
    {:data {:middleware [exception/exception-middleware]}})
   (ring/create-default-handler)))

(def reloadable-app (wrap-reload #'app))

(defonce server (run-jetty reloadable-app
                           {:port 3000 :join? false}))
