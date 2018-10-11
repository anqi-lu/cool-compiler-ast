(* wrong method call: not local *) 
class Main {
    i : Int;

    init(j : Str) : SELF_TYPE {
        {
            j <- 1 + 1;
            self;
        }
    };
};

class A inherits Main {

    s1 : Str <- "hello";
    s2 : Str <- "nihao";

    i : Main <- init(s1, s2);

};