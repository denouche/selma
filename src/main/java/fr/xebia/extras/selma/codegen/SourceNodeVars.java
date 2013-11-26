package fr.xebia.extras.selma.codegen;

/**
 * Created with IntelliJ IDEA.
 * User: slemesle
 * Date: 20/11/2013
 * Time: 02:10
 * To change this template use File | Settings | File Templates.
 */
public class SourceNodeVars {

    String field;

    BeanWrapper inBean;

    BeanWrapper outBean;

    String inField;
    String outField;

    InOutType inOutType;

    boolean assign = false;
    private String inFieldPrefix;

    public SourceNodeVars(String field, BeanWrapper inBean, BeanWrapper outBean) {
        this.field = field;
        this.inBean = inBean;
        this.outBean = outBean;
        inField = (inBean == null ? "in" : inBean.getInGetterFor(field));
        outField = (outBean == null ? "out" : outBean.getOutSetterPathFor(field));
        inFieldPrefix = "";
    }

    public SourceNodeVars() {
        this.field = null;
        this.inBean = null;
        this.outBean = null;
        inField = "in";
        outField = "out";
        inFieldPrefix = "";
    }

    public SourceNodeVars(String inField) {
        this.field = inField;
    }

    public SourceNodeVars withInField(String _inField) {
        inField = _inField;
        return this;
    }

    public SourceNodeVars withOutField(String _outField) {
        outField = _outField;
        return this;
    }

    public SourceNodeVars withInOutType(InOutType inOutType) {
        this.inOutType = inOutType;
        return this;
    }

    public SourceNodeVars withAssign(boolean assign) {
        this.assign = assign;
        return this;
    }

    public String inGetter() {
        return inField;
    }

    public String outType() {
        return outBean.getTypeFor(field).toString();
    }

    public String outSetterPath() {
        return outField;
    }

    public String outSetter() {
        return outField + (inBean == null ? "" : "()");
    }

    public MappingSourceNode setOrAssign(String value) {

        String formattedValue = inFieldPrefix + String.format(value, inGetter());

        return (assign ? MappingSourceNode.assign(outField, formattedValue) : MappingSourceNode.set(outField, formattedValue));
    }

    public String itemVar() {
        return String.format("_%sItem", (field == null ? "out" : field));
    }


    public String itemEntry() {
        return String.format("_%sEntry", (field == null ? "out" : field));
    }

    public String indexVar() {
        return String.format("_%sIndex", (field == null ? "out" : field));
    }

    public String indexVar(char indexChar) {
        return String.format("%c%sIndex", indexChar, (field == null ? "out" : field));
    }

    public boolean inTypeIsPrime() {
        return inOutType.areSamePrimitive();
    }


    public String tmpVar(String suffix) {
        return String.format("_%sTmp%s", (field == null ? "out" : field), suffix);
    }

    public String totalCountVar() {
        return String.format("_%sTotalCount", (field == null ? "out" : field));
    }

    public SourceNodeVars withInFieldPrefix(String inFieldPrefix) {
        this.inFieldPrefix = inFieldPrefix;
        return this;
    }
}