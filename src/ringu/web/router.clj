(ns ringu.web.router
  (:require
   [ringu.web.exception :as exception]
   [reitit.ring :as ring]
   [ring.middleware.params :refer [wrap-params]]
   [ringu.web.handlers :as handlers]))

(defn handler [_]
  {:status 200, :body "ok!!zzzz"})

(defn handler_id [{{id :id} :path-params}]
  {:status 200, :body (str "zz " id)})

(def routes [["/" handlers/home]
             ["/suppliers" handlers/suppliers]
             ["/fail" (fn [_] (throw (ex-info "fail" {:type ::failure})))]
             ["/pang/:id" {:get handler_id :name :pang}]
             ["/pong" {:get handler :name :pong}]
             ["/ping" {:get handler :name :ping}]])

(def router (ring/router
             routes
             {:data {:middleware [wrap-params exception/exception-middleware]}}))
