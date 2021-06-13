if [ -z $1 ]; then
    echo "Pass the argument. 1 for running test for V1 and 2 for V2"
    exit 1
fi

javac ThreadDemoV$1.java
for i in {1..10}
do
    >actual.txt
    java ThreadDemoV$1 > actual.txt
    diff actual.txt expected.txt
    echo "Done $i"
done
