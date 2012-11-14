package com.peergreen.prototype.test;

import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.options;

import org.ops4j.pax.exam.ExamSystem;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.TestContainer;
import org.ops4j.pax.exam.spi.PaxExamRuntime;
public class LaunchClient {

    public static void main( String[] args )
            throws Exception
    {
        // The usual configuration
        String gogoVersion = "0.8.0";
        Option[] options = options(
                mavenBundle().groupId("org.apache.felix").artifactId("org.apache.felix.ipojo").version("1.8.4"),
                mavenBundle().groupId("org.apache.felix").artifactId("org.apache.felix.ipojo.arch.gogo").version("1.0.1"),
                mavenBundle().groupId( "com.peergreen.prototype" ).artifactId( "featurecontext-prototype" ),
                mavenBundle().groupId("org.apache.felix").artifactId("org.apache.felix.gogo.runtime").version(gogoVersion),
                mavenBundle().groupId("org.apache.felix").artifactId("org.apache.felix.gogo.shell").version(gogoVersion),
                mavenBundle().groupId("org.apache.felix").artifactId("org.apache.felix.gogo.command").version(gogoVersion)

        );


        // create a proper ExamSystem with your options. Focus on "createServerSystem"
        ExamSystem system = PaxExamRuntime.createTestSystem( options );

        // create Container (you should have exactly one configured!) and start.
        TestContainer container = PaxExamRuntime.createContainer( system );
        container.start();

    }
}
