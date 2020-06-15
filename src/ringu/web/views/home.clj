(ns ringu.web.views.home
  (:require [ringu.web.views.helpers :as helpers]))

(defn index-page [req]
  (helpers/layout "Δοκιμή" [:div "Αρχική σελίδα"])
  )