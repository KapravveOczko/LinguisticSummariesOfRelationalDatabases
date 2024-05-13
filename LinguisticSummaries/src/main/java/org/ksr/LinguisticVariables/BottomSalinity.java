package org.ksr.LinguisticVariables;
import org.ksr.LinguisticVariables.MembershipFunction.MembershipFactoryConsts;
import org.ksr.LinguisticVariables.MembershipFunction.MembershipFunctionFactory;

public class BottomSalinity extends LinguisticVariable{

    public BottomSalinity(String name) {
        super(name);
    }

    public void appendMembershipFunctions(){
        appendMembershipFunctions(MembershipFunctionFactory.createMembershipFunction(MembershipFactoryConsts.TRAPEZOIDAL, "not salty",  Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 22.0, 26.0));
        appendMembershipFunctions(MembershipFunctionFactory.createMembershipFunction(MembershipFactoryConsts.TRAPEZOIDAL, "moderately salty", 24.0, 26.0, 30.5, 32.0));
        appendMembershipFunctions(MembershipFunctionFactory.createMembershipFunction(MembershipFactoryConsts.TRIANGULAR, "strongly salty", 30.5, 34.0, 36.0));
        appendMembershipFunctions(MembershipFunctionFactory.createMembershipFunction(MembershipFactoryConsts.TRAPEZOIDAL, "really salty", 35.0, 36.0, Double.POSITIVE_INFINITY,  Double.POSITIVE_INFINITY));
    }
}
