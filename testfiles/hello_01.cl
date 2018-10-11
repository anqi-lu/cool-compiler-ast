class A {
    hello() : B {
        1
    };
};


class B inherits A {
} ;


class C inherits B {
} ;


class Main inherits B {
    i : Int;

    init(j : Str) : SELF_TYPE {
        {
            j <- "hello";
            self;
        }
    };

} ;

