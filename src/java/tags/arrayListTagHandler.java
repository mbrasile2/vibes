/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tags;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author kATHRYN
 */
import java.util.List;

public class arrayListTagHandler extends SimpleTagSupport {

    public static boolean contains(List list, Object o) {
      return list.contains(o);
   }
    
    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

            
        } catch (java.io.IOException ex) {
            throw new JspException("Error in arrayListTagHandler tag", ex);
        }
    }
    
}
