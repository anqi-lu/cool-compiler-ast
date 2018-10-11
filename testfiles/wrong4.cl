(* wrong type of parameter *) 
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

    i : Main <- (new Main).init(s1, s2);

};