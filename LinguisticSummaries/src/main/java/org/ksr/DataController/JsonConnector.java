package org.ksr.DataController;

import org.ksr.FuzzyLib.FuzzySet.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonConnector {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public void saveFuzzySetToFile(FuzzySet fuzzySet, String filePath) throws IOException {
        FuzzySetDTO dto = fuzzySetToDTO(fuzzySet);
        objectMapper.writeValue(new File(filePath), dto);
    }

    public FuzzySet loadFuzzySetFromFile(String filePath) throws IOException {
        FuzzySetDTO dto = objectMapper.readValue(new File(filePath), FuzzySetDTO.class);
        return dtoToFuzzySet(dto);
    }

    public void saveAllFuzzySetsToFolder(List<FuzzySet> fuzzySets, String folderName) throws IOException {
        String assetsDirectory = Paths.get("Assets", folderName).toAbsolutePath().toString();
        for (FuzzySet set : fuzzySets) {
            String filePath = assetsDirectory + File.separator + set.getName() + ".json";
            saveFuzzySetToFile(set, filePath);
        }
    }

    public List<FuzzySet> loadAllFuzzySetsFromFolder(String folderName) throws IOException {
        String assetsDirectory = Paths.get("Assets", folderName).toAbsolutePath().toString();
        List<FuzzySet> fuzzySets = new ArrayList<>();
        File folder = new File(assetsDirectory);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
        if (files != null) {
            for (File file : files) {
                fuzzySets.add(loadFuzzySetFromFile(file.getAbsolutePath()));
            }
        }
        return fuzzySets;
    }

    private FuzzySetDTO fuzzySetToDTO(FuzzySet fuzzySet) {
        if (fuzzySet instanceof TrapezoidalFuzzySet) {
            TrapezoidalFuzzySet trapezoidalFuzzySet = (TrapezoidalFuzzySet) fuzzySet;
            return new FuzzySetDTO(trapezoidalFuzzySet.getA(), trapezoidalFuzzySet.getB(),
                    trapezoidalFuzzySet.getC(), trapezoidalFuzzySet.getD(),
                    trapezoidalFuzzySet.getName(), FuzzySetConstants.TRAPEZOIDAL);
        } else if (fuzzySet instanceof TriangularFuzzySet) {
            TriangularFuzzySet triangularFuzzySet = (TriangularFuzzySet) fuzzySet;
            return new FuzzySetDTO(triangularFuzzySet.getA(), triangularFuzzySet.getB(), triangularFuzzySet.getC(), null,
                    triangularFuzzySet.getName(), FuzzySetConstants.TRIANGULAR);
        } else if (fuzzySet instanceof GaussianFuzzySet) {
            GaussianFuzzySet gaussianFuzzySet = (GaussianFuzzySet) fuzzySet;
            return new FuzzySetDTO(gaussianFuzzySet.getA(), gaussianFuzzySet.getB(), null, null,
                    gaussianFuzzySet.getName(), FuzzySetConstants.GAUSSIAN);
        }
        throw new IllegalArgumentException("Unknown FuzzySet type: " + fuzzySet.getClass().getSimpleName());
    }

    private FuzzySet dtoToFuzzySet(FuzzySetDTO dto) {
        return FuzzySetFactory.createMembershipFunction(dto.getType(), dto.getName(),
                dto.getA(), dto.getB(), dto.getC(), dto.getD());
    }
}
