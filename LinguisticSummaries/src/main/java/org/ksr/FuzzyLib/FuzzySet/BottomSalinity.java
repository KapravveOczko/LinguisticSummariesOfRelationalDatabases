package org.ksr.FuzzyLib.FuzzySet;
import org.ksr.FuzzyLib.MembershipFunction.MembershipFactoryConsts;
import org.ksr.FuzzyLib.MembershipFunction.MembershipFunctionFactory;

public class BottomSalinity extends FuzzySet {

    public BottomSalinity(String name) {
        super(name);
    }

    public void appendMembershipFunctions(){
        appendMembershipFunction(MembershipFunctionFactory.createMembershipFunction(MembershipFactoryConsts.TRAPEZOIDAL, "not salty",  Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 22.0, 26.0));
        appendMembershipFunction(MembershipFunctionFactory.createMembershipFunction(MembershipFactoryConsts.TRAPEZOIDAL, "moderately salty", 24.0, 26.0, 30.5, 32.0));
        appendMembershipFunction(MembershipFunctionFactory.createMembershipFunction(MembershipFactoryConsts.TRIANGULAR, "strongly salty", 30.5, 34.0, 36.0));
        appendMembershipFunction(MembershipFunctionFactory.createMembershipFunction(MembershipFactoryConsts.TRAPEZOIDAL, "really salty", 35.0, 36.0, Double.POSITIVE_INFINITY,  Double.POSITIVE_INFINITY));
    }
}
