class Main {
    i : Int;
    j : Int;

    init(j : Str) : SELF_TYPE {
        (let x : SELF_TYPE <- new Main in {
            i <- 1;
        })
    };
};