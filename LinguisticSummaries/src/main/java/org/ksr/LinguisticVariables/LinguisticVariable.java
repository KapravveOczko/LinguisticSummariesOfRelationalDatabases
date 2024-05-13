package org.ksr.LinguisticVariables;

import org.ksr.LinguisticVariables.MembershipFunction.MembershipFunction;

import java.util.ArrayList;
import java.util.List;

public abstract class LinguisticVariable {
    private String name;
    private List<MembershipFunction> membershipFunctions;

    public LinguisticVariable(String name) {
        this.name = name;
        this.membershipFunctions = new ArrayList<MembershipFunction>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MembershipFunction> getMembershipFunctions() {
        return membershipFunctions;
    }

    public void setMembershipFunctions(List<MembershipFunction> membershipFunctions) {
        this.membershipFunctions = membershipFunctions;
    }

    public void appendMembershipFunctions(MembershipFunction membershipFunction){
        this.membershipFunctions.add(membershipFunction);
    }
}
