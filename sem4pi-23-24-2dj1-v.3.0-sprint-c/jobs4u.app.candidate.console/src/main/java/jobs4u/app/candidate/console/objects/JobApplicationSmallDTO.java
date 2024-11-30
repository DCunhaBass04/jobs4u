package jobs4u.app.candidate.console.objects;

import lombok.Getter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Getter
public class JobApplicationSmallDTO {
    private final int id;
    private final String state;
    private final long numApplicants;

    public JobApplicationSmallDTO(byte[] bytes) {
        byte[] result = new byte[4];
        int i = 0, j;
        for(int k=0;k<result.length;k++) result[k] = bytes[i++];
        id = new BigInteger(result).intValue();
        List<Byte> list = new ArrayList<>();
        while (bytes[i] != '0') list.add(bytes[i++]);
        i++;
        result = new byte[list.size()];
        for (int k = 0; k < list.size(); k++) result[k] = list.get(k);
        state = new String(result);
        result = new byte[8];
        for (j = 0; j < result.length; j++) result[j] = bytes[i++];
        numApplicants = new BigInteger(result).longValue();
    }

    public String toString() {
        return String.format("Job Application %d%n State: %s%n Number of total applicants for the Job Opening: %d%n",
                id, state, numApplicants);
    }
}