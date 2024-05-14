package org.ksr.FuzzyLib.FuzzySet;

import org.ksr.FuzzyLib.MembershipFunction.MembershipFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class FuzzySet<T> {
    private String name;
    private List<MembershipFunction> membershipFunctions;
    private Boolean isConcave;
    private Double cardinality;
    private List<T> support;


    public FuzzySet(String name) {
        this.name = name;
        this.membershipFunctions = new ArrayList<MembershipFunction>();
    }

//    obsolete:
//    public void appendMembershipFunction(MembershipFunction membershipFunction){
//        this.membershipFunctions.add(membershipFunction);
//    }

    public void appendMembershipFunction(MembershipFunction membershipFunction){
        Optional<MembershipFunction> functionToRemove = this.membershipFunctions.stream()
                .filter(function -> membershipFunction.getName().equals(function.getName()))
                .findFirst();
        if (functionToRemove.isPresent()) {
            throw new RuntimeException("There is a membership functions with the specified name");
        } else {
            this.membershipFunctions.add(membershipFunction);
        }
    }

    public void deleteMembershipFunction(String functionName) {
        Optional<MembershipFunction> functionToRemove = this.membershipFunctions.stream()
                .filter(function -> functionName.equals(function.getName()))
                .findFirst();
        if (functionToRemove.isPresent()) {
            this.membershipFunctions.remove(functionToRemove.get());
        } else {
            throw new RuntimeException("There are no membership functions with the specified name");
        }
    }


    public void setConcave(){

    }

    public void setSupport(){

    }

    public void getAlphaCut(double alpha){

    }


}
