package org.ksr.Assets;

import org.ksr.DataController.JsonConnector;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Assets {

    private List<LinguisticVariable> variables;

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
        variables.add(new AbsoluteQuantifiers());
        variables.add(new RelativeQuantifiers());

        this.variables = variables;
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



}