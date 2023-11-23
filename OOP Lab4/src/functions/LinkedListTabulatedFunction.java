package functions;

import java.io.Serializable;

public class LinkedListTabulatedFunction  implements TabulatedFunction, Serializable
{

    private class FunctionNode
    {
        private FunctionPoint value;
        private FunctionNode next;
        private FunctionNode prev;
        FunctionNode()
        {
            value = new FunctionPoint(0,0);
            next = null;
            prev = null;
        }
        FunctionNode(FunctionNode node)
        {
            value = node.value;
            next = node.next;
            prev = node.prev;
        }
        FunctionNode(double x, double y, FunctionNode prev)
        {
            this.value = new FunctionPoint(x, y);
            this.next = null;
            this.prev = prev;
        }
        public FunctionPoint getValue()
        {
            return value;
        }
        private void setValue(FunctionPoint point)
        {
            value = point;
        }
        private void setNext(FunctionNode node)
        {
            this.next = node;
            node.prev = this;
        }
    }

    private final FunctionNode head;
    private int pointsCount;

    public LinkedListTabulatedFunction()
    {
        head = new FunctionNode();
        pointsCount = 0;
    }

    public LinkedListTabulatedFunction(FunctionPoint array[])
    {
        if (array.length < 2)
            throw new IllegalArgumentException("Error! PointsCount < 2");
        if (!(xSorted(array)))
            throw new IllegalArgumentException(("Error! X are not sorted!"));
        pointsCount = array.length;
        head = new FunctionNode();
        FunctionNode node = new FunctionNode(array[0].getX(), array[0].getY(), head);
        head.setNext(node);
        FunctionNode prev = node;
        for (int i = 1; i < pointsCount; ++i)
        {
            node = new FunctionNode(array[i].getX(), array[i].getY(), prev);
            prev.setNext(node);
            prev = node;
        }
        node.next = head.next;
        head.next.prev = node;
    }

    public LinkedListTabulatedFunction(double leftX, double rightX, int pointsCount)
    {
        if (leftX >= rightX || pointsCount < 2)
            throw new IllegalArgumentException("Error! leftX >= rightX || pointsCount < 2");
        double inter = Math.abs(rightX - leftX + 1) / pointsCount;
        this.pointsCount = pointsCount;
        head = new FunctionNode();
        FunctionNode node = new FunctionNode(leftX, 0, head);
        head.setNext(node);
        FunctionNode prev = node;
        for (int i = 1; i < pointsCount; ++i)
        {
            node = new FunctionNode(leftX + i * inter, 0, prev);
            prev.setNext(node);
            prev = node;
        }
        node.next = head.next;
        head.next.prev = node;
    }

    public LinkedListTabulatedFunction(double leftX, double rightX, double[] values)
    {
        if (leftX >= rightX || values.length < 2)
            throw new IllegalArgumentException("Error! leftX >= rightX || pointsCount < 2");
        double inter = (rightX - leftX + 1) / values.length;
        pointsCount = values.length;
        head = new FunctionNode();
        FunctionNode node = new FunctionNode(leftX, values[0], head);
        head.setNext(node);
        FunctionNode prev = node;
        for (int i = 1; i < pointsCount; ++i)
        {
            node = new FunctionNode(leftX + i * inter, values[i], prev);
            prev.setNext(node);
            prev = node;
        }
        node.next = head.next;
        head.next.prev = node;
    }

    private boolean xSorted(FunctionPoint array[])
    {
        for (int i = 0; i < array.length - 1; ++i)
        {
            if (array[i + 1].getX() > array[i].getX())
                return false;
        }
        return true;
    }

    private FunctionNode getNodeByIndex(int index)
    {
        if (index == 0)
        {
            FunctionNode node = head.next;
            return node;
        }
        if (index == pointsCount - 1)
        {
            FunctionNode node = head.next.prev;
            return node;
        }
        if (index > 0 && index < pointsCount - 1)
        {
            FunctionNode node = head.next.next;
            for (int i = 1; i < index; ++i)
            {
                node = node.next;
            }
            return node;
        }
        else
            throw new FunctionPointIndexOutOfBoundsException("Error! Point index is out of function range");
    }

    private FunctionNode addNodeToTail()
    {
        FunctionNode node = new FunctionNode();
        if (pointsCount == 0)
        {
            head.next = node;
            head.prev = node;
            node.next = node;
            node.prev = node;
            return node;
        }
        node.next = head.next;
        head.next.prev.next = node;
        node.prev = head.next.prev;
        head.next.prev = node;
        ++pointsCount;
        return node;
    }

    private FunctionNode addNodeByIndex(int index)
    {
        if (index >= 0 && index <= pointsCount)
        {
            if (pointsCount == 0 || index == pointsCount)
            {
                ++pointsCount;
                return addNodeToTail();
            }
            FunctionNode node = head.next;
            for (int i = 0; i < index; ++i)
            {
                node = node.next;
            }
            FunctionNode new_node = new FunctionNode();
            new_node.next = node;
            new_node.prev = node.prev;
            node.prev.next = new_node;
            node.prev= new_node;
            ++pointsCount;
            return new_node;
        }
        else
            throw new FunctionPointIndexOutOfBoundsException("Error! Point index is out of function range");
    }

    private FunctionNode deleteNodeByIndex(int index)
    {
        if (index >= 0 && index < pointsCount)
        {
            FunctionNode node = head.next;
            for (int i = 0; i < index; ++i)
            {
                node = node.next;
            }
            node.next.prev = node.prev;
            node.prev.next = node.next;
            --pointsCount;
            return node;
        }
        else
            throw new FunctionPointIndexOutOfBoundsException("Error! Point index is out of function range");
    }

    public double getLeftDomainBorder()
    {
        return getNodeByIndex(0).getValue().getX();
    }

    public double getRightDomainBorder()
    {
        return getNodeByIndex(pointsCount - 1).getValue().getX();
    }

    public double getFunctionValue(double x)
    {
        return (x >= getNodeByIndex(0).getValue().getX() && x <= getNodeByIndex(pointsCount - 1).getValue().getX()) ?
                (((getNodeByIndex(pointsCount - 1).getValue().getY() - getNodeByIndex(0).getValue().getY()) /
                        (getNodeByIndex(pointsCount - 1).getValue().getX() - getNodeByIndex(0).getValue().getX())) *
                        (x - getNodeByIndex(pointsCount - 1).getValue().getX()) + getNodeByIndex(pointsCount - 1).getValue().getY())
                : Double.NaN;
    }

    public int getPointsCount()
    {
        return pointsCount;
    }

    public FunctionPoint getPoint(int index)
    {
        if (index >= 0 && index < pointsCount)
            return getNodeByIndex(index).getValue();
        else
            throw new FunctionPointIndexOutOfBoundsException("Error! Point index is out of function range");
    }

    public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException, FunctionPointIndexOutOfBoundsException
    {
        if (index >= 0 && index < pointsCount)
        {
            if ((point.getX() >= getNodeByIndex(0).getValue().getX() && point.getX() <= getNodeByIndex(pointsCount - 1).getValue().getX()) ||
                    (point.getX() < getNodeByIndex(0).getValue().getX() && index == 0) ||
                    (point.getX() > getNodeByIndex(pointsCount - 1).getValue().getX() && index == pointsCount - 1))
                addNodeByIndex(index).setValue(point);
            else
                throw new InappropriateFunctionPointException("Error! X is out of function range");
        } else
            throw new FunctionPointIndexOutOfBoundsException("Error! Point index is out of function range");
    }

    public double getPointX(int index)
    {
        if (index >= 0 && index < pointsCount)
            return getNodeByIndex(index).getValue().getX();
        else
            throw new FunctionPointIndexOutOfBoundsException("Error! Point index is out of function range");
    }

    public void setPointX(int index, double x) throws InappropriateFunctionPointException, FunctionPointIndexOutOfBoundsException
    {
        if (index >= 0 && index < pointsCount)
        {
            if ((x >= getNodeByIndex(0).getValue().getX() && x <= getNodeByIndex(pointsCount - 1).getValue().getX()) || (x < getNodeByIndex(0).getValue().getX() &&
                    index == 0) || (x > getNodeByIndex(pointsCount - 1).getValue().getX() && index == pointsCount - 1))
                getNodeByIndex(index).getValue().setX(x);
            else
                throw new InappropriateFunctionPointException("Error! X is out of function range");
        } else
            throw new FunctionPointIndexOutOfBoundsException("Error! Point index is out of function range");
    }

    public double getPointY(int index)
    {
        if (index >= 0 && index < pointsCount)
            return getNodeByIndex(index).getValue().getY();
        else
            throw new FunctionPointIndexOutOfBoundsException("Error! Point index is out of function range");
    }

    public void setPointY(int index, double y)
    {
        if (index >= 0 && index < pointsCount)
        {
            if (index == 0 || index == pointsCount - 1)
            {
                getNodeByIndex(index).getValue().setY(y);
                return;
            }
            double x = (((getNodeByIndex(pointsCount - 1).getValue().getX() - getNodeByIndex(0).getValue().getX()) /
                    (getNodeByIndex(pointsCount - 1).getValue().getY() - getNodeByIndex(0).getValue().getY())) *
                    (y - getNodeByIndex(pointsCount - 1).getValue().getY()) + getNodeByIndex(pointsCount - 1).getValue().getX());
            if (x >= getNodeByIndex(0).getValue().getX() && x <= getNodeByIndex(pointsCount - 1).getValue().getX())
                getNodeByIndex(index).getValue().setY(y);
        } else
            throw new FunctionPointIndexOutOfBoundsException("Error! Point index is out of function range");
    }

    public void deletePoint(int index)
    {
        if (pointsCount < 3)
            throw new IllegalStateException("pointsCount < 3");
        deleteNodeByIndex(index);
    }

    //For addPoint method
    private int interpolationSearch(double x)
    {
        if (getNodeByIndex(0).getValue().getX()> x)
            return 0;
        if (getNodeByIndex(pointsCount - 1).getValue().getX() <= x)
            return pointsCount - 1;
        int mid;
        int low = 0;
        int high = pointsCount - 1;
        while (getNodeByIndex(low).getValue().getX() < x && getNodeByIndex(high).getValue().getX() > x)
        {
            if (getNodeByIndex(high).getValue() == getNodeByIndex(low).getValue()) // Защита от деления на 0
                break;

            mid = (int) (low + ((x - getNodeByIndex(low).getValue().getX()) * (high - low)) /
                    (getNodeByIndex(high).getValue().getX() - getNodeByIndex(low).getValue().getX()));

            if (getNodeByIndex(mid).getValue().getX() == x) // already existing point
                return -1;

            if (getNodeByIndex(mid).getValue().getX() < x)
                low = mid + 1;
            else if (getNodeByIndex(mid).getValue().getX() > x)
                high = mid - 1;
            else
                return mid;
        }

        if ((getNodeByIndex(low).getValue().getX() == x) || (getNodeByIndex(high).getValue().getX() == x)) // already existing point
            return -1;

        if (getNodeByIndex(low).getValue().getX() > x)
            return low;

        if (getNodeByIndex(high).getValue().getX() < x)
            return high;

        return -1; // Not found
    }


    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException
    {
        int index = interpolationSearch(point.getX());
        if (index == -1)
            throw new InappropriateFunctionPointException("Error! Point with this X already exists");
        setPoint(index, point);
    }
}
