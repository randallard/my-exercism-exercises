(ns sublist)

(defn get-smaller-bigger-and-potential-result
  "takes list 1 and list 2, returns nil if both lists are same size, otherwise returns the smaller list as smaller, bigger list as bigger, potential result is :sublist if (= list1 smaller) else :superlist"
  [list1 list2]
  (let [elements-in-list-1 (count list1)
        elements-in-list-2 (count list2)]
      (if (= elements-in-list-1 elements-in-list-2)
        nil
        (if (> elements-in-list-1 elements-in-list-2)
          { :potential-result :superlist
            :smaller          list2
            :bigger           list1 }
          { :potential-result :sublist
            :smaller          list1
            :bigger           list2 }))))

(comment
  (clojure.test/test-vars
    [#'sublist-test/get-smaller-bigger-and-potential-result-returns-bigger-list-when-first])
  (clojure.test/test-vars
    [#'sublist-test/get-smaller-bigger-and-potential-result-returns-bigger-list-when-second])
  (clojure.test/test-vars
    [#'sublist-test/get-smaller-bigger-and-potential-result-returns-smaller-list-when-first])
  (clojure.test/test-vars
    [#'sublist-test/get-smaller-bigger-and-potential-result-returns-smaller-list-when-second])
  (clojure.test/test-vars
    [#'sublist-test/get-smaller-bigger-and-potential-result-returns-potential-as-superlist])
  (clojure.test/test-vars
    [#'sublist-test/get-smaller-bigger-and-potential-result-returns-nil-if-same-number-of-elements])
  (clojure.test/test-vars
    [#'sublist-test/get-smaller-bigger-and-potential-result-returns-potential-as-sublist])
  (clojure.test/run-tests 'sublist-test)
  )

(defn define-contained-list [list1 list2]
  (let [{:keys [smaller bigger potential-result]} (get-smaller-bigger-and-potential-result list1 list2)]
    (loop [bigger-now bigger
           result false]
      (if (and (>= (count bigger-now) (count smaller)) (not result) (not (nil? potential-result)))
        (recur (drop 1 bigger-now) (= smaller (take (count smaller) bigger-now)))
        (if result
          potential-result
          :unequal)))))
(comment
  (clojure.test/test-vars
    [#'sublist-test/define-contained-list-returns-sublist])
  (clojure.test/test-vars
    [#'sublist-test/define-contained-list-returns-superlist])
  (clojure.test/test-vars
    [#'sublist-test/define-contained-list-returns-unequal])
  )

(defn classify [list1 list2]
  (if (= list1 list2)
    :equal
    (define-contained-list list1 list2)))

(comment
  (clojure.test/run-tests 'sublist-test)
  ;; create a list of same sized lists with drop / take
  ;; filter to an equal list

  (let [smaller [2 3]
        bigger [1 2 3]]
    (loop [bigger-now bigger
           result false]
      (if (and (>= (count bigger-now) (count smaller)) (not result))
        (recur (drop 1 bigger-now) (= smaller (take (count smaller) bigger-now)))
        result)))

  (let [smaller [2]
        bigger [1 2 3]]
    (loop [bigger-now bigger
           result false]
      (if (and (>= (count bigger-now) (count smaller)) (not result))
        (recur (drop 1 bigger-now) (= smaller (take (count smaller) bigger-now)))
        result)))

  (let [smaller [4]
        bigger [1 2 3]]
    (loop [bigger-now bigger
           result false]
      (if (and (>= (count bigger-now) (count smaller)) (not result))
        (recur (drop 1 bigger-now) (= smaller (take (count smaller) bigger-now)))
        result)))

  (let [smaller [4]
        bigger [1 2 3]]
    (loop [bigger-now bigger
           result false]
      (if (and (>= (count bigger-now) (count smaller)) (not result))
        (recur (drop 1 bigger-now) (= smaller (take (count smaller) bigger-now)))
        result)))


  ;; let smaller be the list with fewer elements
  ;;     bigger be the list with more elements
  ;;     num-elements be the count of elements in the smaller list
  ;; reverse both lists and see if (= (take num-elements bigger) smaller)
  ;; (pop bigger) and try again
  ;; return :sublist if (= list1 smaller) else :superlist
  )
