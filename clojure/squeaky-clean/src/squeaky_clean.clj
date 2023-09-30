(ns squeaky-clean
  (:require [clojure.string :as str]))

; replace any spaces with underscores - including any leading and trailing spaces
(defn replace-spaces [s]
  (str/replace s \space \_)
  )
(comment
  (let [s " A"]
    (str/replace s \space \_))
  (replace-spaces " A")
  (replace-spaces  " *  Ab c de _ ")
  (clojure.test/run-tests 'squeaky-clean-test)
  )


(comment
  (str/replace "a1_b2_" #"[^A-Za-z_]" "")
  )
(defn clean
  "returns a new string, based on the parameter after the following transformations
      replace all spaces with underscores, including leading and trailing spaces"
  [s]
  (replace-spaces s)
  )

(comment
  (clojure.test/run-tests 'squeaky-clean-test)
  )