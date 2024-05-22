package org.ksr.Assets;

import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import java.util.ArrayList;
import java.util.List;

public class Assets {

    private List<LinguisticVariable> variables;
    private List<LinguisticVariable> qualifiers;

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

        ArrayList<LinguisticVariable> qualifiers = new ArrayList<>();
        qualifiers.add(new AbsoluteQuantifiers());
        qualifiers.add(new RelativeQuantifiers());

        this.variables = variables;
        this.qualifiers = qualifiers;
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

    public List<LinguisticVariable> getQualifiers() {
        return qualifiers;
    }
}
