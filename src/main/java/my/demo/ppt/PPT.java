package my.demo.ppt;

import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;

public class PPT {
    public static void main(String[] args) {

        // instantiate a Presentation object that represents a presentation file
        Presentation pres = new Presentation("/Users/wyb/IdeaProjects/back3d/java-demo/data/demo.pptx");
        try {
            // save the presentation to PDF with default options
            pres.save("/Users/wyb/IdeaProjects/back3d/java-demo/data/output.pdf", SaveFormat.Pdf);
        } finally {
            pres.dispose();
        }

    }
}
