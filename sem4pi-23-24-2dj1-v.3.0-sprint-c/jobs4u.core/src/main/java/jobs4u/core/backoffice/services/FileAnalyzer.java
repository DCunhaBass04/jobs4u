package jobs4u.core.backoffice.services;

import eapli.framework.general.domain.model.EmailAddress;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileAnalyzer {
    public static String getContentFromFileName(String fileName) throws IOException {
        return Files.readString(Path.of(fileName));
    }
    public static EmailAddress getCandidateEmail(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        br.readLine();
        return EmailAddress.valueOf(br.readLine());
    }
}
