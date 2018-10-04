class Main inherits IO {
    main() : SELF_TYPE {
	(let c : Complex <- (new Complex).init(1, 1) in
	    if c.reflect_X().reflect_Y() = c.reflect_0()
	    then outStr("=)\n")
	    else outStr("=(\n")
	    fi
	)
    };
};

class Complex inherits IO {
    x : Int;
    y : Int;

    init(a : Int, b : Int) : Complex {
	{
	    x = a;
	    y = b;
	    self;
	}
    };

    print() : Object {
	if y = 0
	then outInt(x)
	else outInt(x).outStr("+").outInt(y).outStr("I")
	fi
    };

    reflect_0() : Complex {
	{
	    x = -x;
	    y = -y;
	    self;
	}
    };

    reflect_X() : Complex {
	{
	    y = -y;
	    self;
	}
    };

    reflect_Y() : Complex {
	{
	    x = -x;
	    self;
	}
    };
};
