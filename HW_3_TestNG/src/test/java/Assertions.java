import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import triangle.Triangle;

public class Assertions {

    Triangle triangle;

    /*
     * Checking the function of defining a shape as a triangle - data set for true cases
     */
    @DataProvider (name = "checkTriangleTrueAssumptions")
    public static Object[][] mainOrdinaryTrueAssumptions(){
        return new Object[][]{{3,4,5}, {4,3,5}, {5,4,3}, {5,3,4}, {3,5,4}, {4,5,3},
                {3,3,3},
                {2,2,3}, {3,2,2}, {2,3,2}
        };
    }

    @Test (dataProvider = "checkTriangleTrueAssumptions", groups = {"group1"})
    public void startTrueAssumptionsTest(double a, double b, double c){
        triangle = new Triangle(a, b, c);
        Assert.assertNotEquals(triangle.checkTriangle(), false);
    }

    /*
     * Checking the function of defining a shape as a triangle - data set for false cases
     */
    @DataProvider (name = "checkTriangleFalseAssumptions")
    public static Object[][] mainOrdinaryFalseAssumptions(){
        return new Object[][]{{0,3,4}, {3,0,4}, {4,0,3},
                {-3,3,0}, {3,0,-3}, {0,-3,3},
                {0,0,0},
                {2,2,4}, {4,2,2}, {2,4,2},
                {-3,-3,0}
        };
    }

    @Test (dataProvider = "checkTriangleFalseAssumptions", groups = {"group1", "errors"})
    public void startFalseAssumptionsTest(double a, double b, double c){
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.checkTriangle(), false);
    }

    /*
     * Checking the output of a message about incorrect input of side lengths <= 0
     */
    @DataProvider (name = "checkSideLengthMessage")
    public static Object[][] sideMessageAssumptions(){
        return new Object[][]{{3,4,5,""}, {3,3,3,""}, {2,2,3,""},
                {0,3,4,"a<=0"}, {3,0,4,"b<=0"}, {3,4,0,"c<=0"}, {0,0,0,"a<=0"},
                {-1,4,6,"a<=0"}, {4,-1,6,"b<=0"}, {6,4,-1,"c<=0"}
                };
    }

    @Test (dataProvider = "checkSideLengthMessage", groups = {"group2", "errors"})
    public void sideMessageTest(double a, double b, double c, String massage){
        triangle = new Triangle(a, b, c);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(), massage);
    }

    /*
     * Checking the output of a message about incorrect definition of a figure as a triangle
     * by the sum of the lengths of its sides
     */
    @DataProvider (name = "checkTriangleMessage")
    public static Object[][] triangleMessageAssumptions(){
        return new Object[][]{{3,4,5,""}, {3,3,3,""}, {2,2,3,""},
                {2,2,4,"a+b<=c"}, {2,4,2,"a+c<=b"}, {4,2,2,"b+c<=a"},
        };
    }

    @Test (dataProvider = "checkTriangleMessage", groups = {"group2", "errors"})
    public void triangleMessageTest(double a, double b, double c, String massage){
        triangle = new Triangle(a, b, c);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(), massage);
    }


    /*
     * Checking the definition of a right triangle by the sum of the squares of the legs TR_RECTANGULAR = 8
     * Checking the definition of an equilateral triangle TR_EQUILATERAL = 1
     * Checking the definition of an isosceles triangle TR_ISOSCELES = 2
     * Checking the definition of figure is a ordinary triangle TR_ORDYNARY = 4
     */

    @DataProvider (name = "checkTriangleSimpleType")
    public static Object[][] triangleSimpleAssumptions(){
        return new Object[][]{ {3,3,3,1},
                {2,2,3,2}, {3,2,2,2}, {2,3,2,2},
                {2,3,4,4}, {4,2,3,4}, {3,4,2,4}, {2,4,3,4}, {4,3,2,4}, {3,2,4,4},
                {3,4,5,8}, {4,3,5,8}, {5,3,4,8}, {5,4,3,8}, {3,5,4,8}, {4,5,3,8}
        };
    }

    @Test (dataProvider = "checkTriangleSimpleType", groups = {"group3"})
    public void triangleSimpleTypeTest(double a, double b, double c, int type){
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), type);
    }

    /*
     * Checking the definition of a triangle as both rectangular and isosceles
     */
    @DataProvider (name = "checkTriangleMatchType")
    public static Object[][] triangleMatchTypeAssumptions(){
        return new Object[][]{{2,2,2.828427,10}, {2,2.828427,2,10}, {2.828427,2,2,10}};
    }

    @Test (dataProvider = "checkTriangleMatchType", groups = {"group3"})
    public void triangleMatchTypeTest(double a, double b, double c, int type){
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), type);
    }


    /*
     * Checking the triangle square calculation
     */
    @DataProvider (name = "checkSquare")
    public static Object[][] triangleSquareAssumptions(){
        return new Object[][]{ {3,3,3,3.897114},
                {2,2,3,1.984313}, {3,2,2,1.984313}, {2,3,2,1.984313},
                {2,3,4,2.904738}, {4,2,3,2.904738}, {3,4,2,2.904738}, {2,4,3,2.904738}, {4,3,2,2.904738}, {3,2,4,2.904738},
                {3,4,5,6}, {4,3,5,6}, {5,3,4,6}, {5,4,3,6}, {3,5,4,6}, {4,5,3,6},
                {2,2,2.828427,2}, {2,2.828427,2,2}, {2.828427,2,2,2}
        };
    }

    @Test (dataProvider = "checkSquare", groups = {"group4"})
    public void triangleSquareTest(double a, double b, double c, double sq){
        triangle = new Triangle(a, b, c);
        Assert.assertEquals (triangle.getSquare(), sq, 0.00001);
    }

    /*
     * Checking the triangle type if the shape is not a triangle
     */
    @DataProvider (name = "checkTypeIfError")
    public static Object[][] triangleErrorTypeAssumptions(){
        return new Object[][]{{0,3,4}, {3,0,4}, {4,0,3},
                {-3,3,0}, {3,0,-3}, {0,-3,3},
                {0,0,0},
                {2,2,4}, {4,2,2}, {2,4,2},
                {-3,-3,0}
        };
    }

    @Test (dataProvider = "checkTypeIfError", groups = {"group5", "errors"})
    public void triangleTypeIfErrorTest(double a, double b, double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), false);
    }

    /*
     * Checking the triangle square calculation if the shape is not a triangle
     */
    @DataProvider (name = "checkSquareIfError")
    public static Object[][] triangleErrorSquareAssumptions(){
        return new Object[][]{ {0,3,4}, {3,0,4}, {4,0,3},
                {-3,3,0}, {3,0,-3}, {0,-3,3},
                {0,0,0},
                {2,2,4}, {4,2,2}, {2,4,2},
                {-3,-3,0}
        };
    }

        @Test (dataProvider = "checkSquareIfError", groups = {"group5", "errors"})
        public void triangleSquareIfErrorTest(double a, double b, double c){
            triangle = new Triangle(a, b, c);
            triangle.checkTriangle();
            Assert.assertEquals (triangle.getSquare(), false);
        }

}
