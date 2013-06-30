# pkts.io

pkts.io is a pure java library for reading and writing pcaps. It's primary purpose is to manipulate/analyze existing pcaps, allowing you to build various tools around pcaps.

Example, load a pcap from file and print the content of each UDP packet to standard out.

```java
import io.pkts.FrameHandler;
import io.pkts.Pcap;
import io.pkts.frame.Frame;
import io.pkts.protocol.Protocol;
import java.io.IOException;
 
// Step 1 - obtain a new Pcap instance by supplying an InputStream that points
//          to a source that contains your captured traffic. Typically you may
//          have stored that traffic in a file so there are a few convenience
//          methods for those cases, such as just supplying the name of the
//          file as shown below.
final Pcap pcap = Pcap.openStream("my_traffic.pcap");
 
// Step 2 - Once you have obtained an instance, you want to start 
//          looping over the content of the pcap. Do this by calling
//          the loop function and supply a FrameHandler, which is a
//          simple interface with only a single method - nextFrame
pcap.loop(new FrameHandler() {
  @Override
  public void nextFrame(final Frame frame) throws IOException {
 
    // Step 3 - For every new frame the FrameHandler will be 
    //          called and you can examine this frame in a few
    //          different ways. You can e.g. check whether the
    //          frame has a particular protocol, such as UDP.
    if (frame.hasProtocol(Protocol.UDP)) {
 
       // Step 4 - Now that we know that the raw frame contains
       //          a UDP packet we get ask to get the UDP frame
       //          and once we have it we can just get its
       //          payload and print it, which is what we are
       //          doing below.
       System.out.println(frame.getFrame(Protocol.UDP).getPayload());
    }
  }
});
```
<noscript>[View gist](https://gist.github.com/aboutsip/5896046)</noscript>

Pkts.io also contains a higher level of abstraction – streams. Quite often you may want to manipulate a stream of related messages and by using the stream support offered by pkts.io, you can do so. What a stream represents depends on the underlying protocol. E.g., for UDP, a stream is all the messages that is sent/received from the same local and remote port-pair. If you deal with SIP, all SIP messages that belongs to the same SIP Dialog are considered to belong to the same stream.

Example, load a pcap from file and consume all SIP Streams.

<script src="https://gist.github.com/aboutsip/5896237.js"></script><noscript>[View gist](https://gist.github.com/aboutsip/5896237)</noscript>

## Consume it

pkts.io is available through Maven Central so just include the following Maven artifact and you are good to go:

      <dependency>
          <groupId>io.pkts</groupId>
          <artifactId>pkts-core</artifactId>
          <version>0.9.4</version>
          <type>jar</type>
      </dependency>

Or if you want to use the stream support, then just include the following:

      <dependency>
          <groupId>io.pkts</groupId>
          <artifactId>pkts-streams</artifactId>
          <version>0.9.4</version>
          <type>jar</type>
      </dependency>

## Documentation

The pkts-examples contains a bunch of different examples showing the various features of the raw frame handling as well as the higher abstraction of streams.

# Contributing
Contributions are more than welcome and highly encouraged. Everything in pkts.io are following the general guidelines of Maven so if you are familiar with Maven you should have no problem getting started.

## Build it

1. Clone it
1. mvn install
1. done

## Import the code into your favorite IDE

Most Java IDE:s are capable of importing Maven based projects, allowing for a smooth integration with the IDE and Maven. Remember, Maven is the source of truth so please do not check in any IDE specific files (such as .project and .classpath for Eclipse).

## Start coding!

So, you have succerssfully loaded all the code so what is the next steps. Well, check out the readme files under each sub-project to get a description of what that module is supposed to do and how to get started with the project. If you want to contribute to any project, please read the section about contribution, specifially, please checkout the section about coding principals and the style guide.

If you have ideas of your own, please file a ticket so when you do submit a pull request, we know what the intent of this feature is, this also gives us a way of communicating about features.

If you just want to get your hands dirty then feel free to check out the open issues. If there is anything you would like to start tackling, just assign the task to yourself and get cranking. 

## Code Principals

At aboutsip, we try to follow these general principals and by adhering to these simple guidelines, we feel that our code is readable, easy to follow and is testable (even though we can always do better so please give us constructive feedback):
* Write code so others can read and understand it.
* Write testable code.
* Unit tests everything, but no need to strive for 100%. Try to hit the 85% mark.
* A method should not be longer than a page (if it is, you probably can split it up).
* Javadoc everything! Please describe what each class/interface is supposed to do. If you cannot explain it, then no one else will be able to understand it either. Each method should be documented. Describe what the method is supposed to do. If you end up writing an essay about the method, perhaps it is too complicated? 


## Style Guide

Please follow the style already in place. If you are an Eclipse user, follow the guidelines for Eclipse below and please load the formatting template and then you won't even have to think about it, Eclipse will do it for you.

If you are using another IDE then please adhere to these basic rules:

In general, the aboutsip code base is following regular java style convention but here are the more important points (and some differences)
* No tabs – ever
* Indent is 4 spaces
* The line width is 120 characters so please do not cut it off at 80 (we all have screens with high enough resolution these days).
* Use final everywhere! (once again, you can have Eclipse fix this for you).
* Use this everywhere! (Eclipse will fix it for you)

