package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static class CharPos {
        char ch;
        int x, y;
        CharPos(char ch, int x, int y) {
            this.ch = ch;
            this.x = x;
            this.y = y;
        }
    }

    public static void printGoogleDocGrid(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();

        // Assuming the table is the first table in the document
        Element table = doc.select("table").first();
        if (table == null) {
            System.out.println("No table found in document.");
            return;
        }

        List<CharPos> chars = new ArrayList<>();
        int maxX = 0;
        int maxY = 0;

        // Iterate over table rows
        Elements rows = table.select("tr");
        for (Element row : rows) {
            Elements cols = row.select("td");
            if (cols.size() < 3) continue; // skip malformed rows

            try {
                int x = Integer.parseInt(cols.get(0).text().trim());
                char ch = cols.get(1).text().trim().charAt(0);
                int y = Integer.parseInt(cols.get(2).text().trim());

                chars.add(new CharPos(ch, x, y));

                if (x > maxX) maxX = x;
                if (y > maxY) maxY = y;
            } catch (NumberFormatException e) {
                // Skip header row or invalid row
                continue;
            }
        }

        // Create grid filled with spaces
        char[][] grid = new char[maxY + 1][maxX + 1];
        for (int i = 0; i <= maxY; i++) {
            for (int j = 0; j <= maxX; j++) {
                grid[i][j] = ' ';
            }
        }

        // Place characters in the grid
        for (CharPos cp : chars) {
            grid[cp.y][cp.x] = cp.ch;
        }

        // Print the grid
        for (int i = 0; i <= maxY; i++) {
            for (int j = 0; j <= maxX; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        String docUrl = "https://docs.google.com/document/d/e/2PACX-1vSvM5gDlNvt7npYHhp_XfsJvuntUhq184By5xO_pA4b_gCWeXb6dM6ZxwN8rE6S4ghUsCj2VKR21oEP/pub";
        printGoogleDocGrid(docUrl);
    }

}