class Main inherits IO {
    pal(s : Str) : Bool {
	if s.length() = 0
	then true
	else if s.length() = 1
	then true
	else if s.substr(0, 1) = s.substr(s.length() - 1, 1)
	then pal(s.substr(1, s.length() -2))
	else false
	fi fi fi
    };

    i : Int;

    main() : SELF_TYPE {
	{
        i <- -1;
	    outStr("enter a string\n");
	    if pal(inStr())
	    then outStr("that was a palindrome\n")
	    else outStr("that was not a palindrome\n")
	    fi;
	}
    };
};
