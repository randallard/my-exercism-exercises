(ns sublist)

(defn nil-or-list-after-remove [the-element the-list]
  #_(if (not (or (vector? the-list) (list? the-list))) (throw (Exception. "the second argument needs to be a list")))
  (if (some #{the-element} the-list)
    (list-after-remove the-element the-list)
    nil))

(defn contained-list? [list1 list2]
  (not (nil? (reduce (fn [the-list the-element]
            (if (nil? the-list)
              nil
              (nil-or-list-after-remove the-element the-list))) list2 list1))))

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (cond (= list1 list2)          :equal
        (contained-list? list1 list2)   :sublist
        (contained-list? list2 list1) :superlist
        :else                    :unequal))

(defn list-after-remove [element1 list1]
  (let [[n m] (split-with (partial not= element1) list1)] (concat n (rest m))))

(comment
  (clojure.test/run-tests 'sublist-test)

  ;; sublist
  ;; sublist if all elements in list1 are in list2
  ;; verify second argument is a list
  (let [the-element 1
        the-list 1]
    (nil-or-list-after-remove the-element the-list))
  ;; nil if element1 isn't in list1
  (let [the-element 3
        the-list [0 1 2]]
    (nil-or-list-after-remove the-element the-list))
  ;; empty list if element1 is last element in list1
  (nil? (let [the-element 3
        the-list [3]]
    (nil-or-list-after-remove the-element the-list)))
  (nil? (let [the-element 3
              the-list [3 nil]]
          (nil-or-list-after-remove the-element the-list)))
  ;; nil-or-list-after-remove each element in a list
  (reduce (fn [the-list the-element]
            (if (nil? the-list)
              nil
              (nil-or-list-after-remove the-element the-list))) [0 1 2 3] [0 1 2])
  (reduce (fn [the-list the-element]
            (if (nil? the-list)
              nil
              (nil-or-list-after-remove the-element the-list))) [0 1 2 3] [0 1 2 4])


  (let [[n m] (split-with (partial not= :b) [:a :b :b :c :b :d])] (concat n (rest m)))

  (let [list1 [1 1 2]
      list2 [0 1 1 1 2 1 2]]
    (classify list1 list2))
  (let [list1 [1 1 2]
        list2 [1 1 2 ]]
    (classify list1 list2))
  (let [list1 [1 1 2]
        list2 [3]]
    (classify list1 list2))
  )
