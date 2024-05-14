package org.ksr.FuzzyLib.MembershipFunction;

public interface MembershipFunction {
    String getName();
    double calculateMembership(double x);
}
