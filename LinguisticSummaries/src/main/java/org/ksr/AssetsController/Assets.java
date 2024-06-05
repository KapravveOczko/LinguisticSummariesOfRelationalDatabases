package org.ksr.AssetsController;

import org.ksr.Assets.*;
import org.ksr.FuzzyLib.FuzzySet.FuzzySet;
import org.ksr.FuzzyLib.LinguisticSummary.Label;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import java.util.ArrayList;
import java.util.List;

public class Assets {

    private List<LinguisticVariable> variables;
    private List<LinguisticVariable> quantifiers;

    public Assets() {

        ArrayList<LinguisticVariable> variables = new ArrayList<>();
        variables.add(new BottomSalinity());
        variables.add(new BottomTemperature());
        variables.add(new Depth());
        variables.add(new Latitude());
        variables.add(new Longitude());
        variables.add(new SurfaceSalinity());
        variables.add(new SurfaceTemperature());
        variables.add(new WaveDirection());
        variables.add(new WaveFrequency());
        variables.add(new WaveHeight());
        variables.add(new WaveSpeed());

        ArrayList<LinguisticVariable> quantifiers = new ArrayList<>();
        quantifiers.add(new AbsoluteQuantifiers());
        quantifiers.add(new RelativeQuantifiers());

        this.variables = variables;
        this.quantifiers = quantifiers;
    }

    public LinguisticVariable getVariable(String name) {
        return getVariables().stream()
                .filter(linguisticVariable -> linguisticVariable.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<LinguisticVariable> getVariables() {
        return variables;
    }

    public List<LinguisticVariable> getQuantifiers() {
        return quantifiers;
    }

    public List<Label> getAllQuantifiers(){
        List<Label> labeledQuantifiers = new ArrayList<>();

        for(LinguisticVariable quantifier : quantifiers){
            for(FuzzySet fuzzySet: quantifier.getFuzzySets()){
                labeledQuantifiers.add(new Label(fuzzySet.getName(), quantifier));
            }
        }

        return labeledQuantifiers;
    }

    public List<Label> getAllVariables(){
        List<Label> labeledVariables = new ArrayList<>();

        for(LinguisticVariable variable : variables){
            for(FuzzySet fuzzySet: variable.getFuzzySets()){
                labeledVariables.add(new Label(fuzzySet.getName(), variable));
            }
        }

        return labeledVariables;
    }
}