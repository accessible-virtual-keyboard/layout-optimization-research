import layout.LetterLogger;
import layout_testing.Dictionary;
import layout_testing.LayoutTester;
import tools.StringUtil;

import java.io.IOException;

/**
 * Uncomment code at the bottom to see some useful data in the output.
 * Some of the output from various methods at the bottom of the file can be copied and pasted into spreadsheets or java
 * code for further usage.
 * Created by Tor Martin Holen on 23-Jan-17.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        class Research {
            public Research() {}

            private LetterLogger setupLogger() throws IOException {
                LetterLogger letterLogger = new LetterLogger();
                letterLogger.sampleLetterOccurrence();
                letterLogger.getStats().findOptimalLayouts(false,false,false);
                return letterLogger;
            }

            /**
             * Prints the first result used in the project report on layout efficiency
             * @throws IOException
             */
            public void testLayout1() throws IOException {
                LetterLogger letterLogger = setupLogger();

                LayoutTester layoutTester = new LayoutTester(letterLogger.getStats().getOptimalLayouts(), 2, 5, 6);
                layoutTester.testText("A hallmark of the time we live in is being able to communicate fast and efficiently across vast distances. We have also dubbed the time period we currently live in as the information age. Being able to efficiently communicate with the world around us have always been of great importance for us human beings, and in the current age, this has become even more important. But there are those among us that because of handicaps have little or no means of communication. In this project we aim to create a system that can be used to improve the quality of life of individuals with varying degree of disability, by enhancing their capabilities to communicate with the world around them. We the project group decided upon this project after much debate. Going into the planning process, there were two major factors that weighed heavy; The first was that we wanted the project to result in an actual product that could potentially make a difference in the life of people. And the second factor was that it should be easy for developers and researchers that come after us to pick up the project and continue our work. Although one project came close, there was no projects available that we thought was a good enough fit, so we decided to create a self-defined project. People are social beings, and for most people being able to communicate with others is an important factor in quality of life. But for some people, communication may not be so easy. Some may be suffering from a medical disorder, such as ALS, or they may have been severely hurt in an accident. Their condition may cause them to be incapable of speaking or using standard methods for writing, such as a pen or a keyboard. This efficiently makes them incapable of communicating, and can cause them a great deal of stress and reduce their quality of life. With our project we want to help some of these people by developing an application that can be used by someone with very limited input capabilities. We want them to be able to write words and sentences, so that they can communicate with the people around them. The following is a description of the goals of our project, and with some added constraints to make the project feasible to complete within our time frame. The application should be runnable on off the shelf hardware running the android operating system. This will make it possible to construct a cheap and portable communication device that can easily be installed onto any existing auxiliary equipment the user may already be using, such as a wheelchair. We will specifically target tablet devices, as we consider this to be the most practical form factor, considering the size of the screen will allow the user interface to be large enough to make it easily readable for the user. The inputs for the application should be only simple Boolean inputs. This can allow for many different input sources, some examples are EEG, EMG, or a simple click pad. Even though there are many possible sources for input, our project will focus only on making the application controllable from an EEG headset. This will allow the user to control the application using only brainwaves. For some severely disabled people, this may be the only source of a signal they are able to produce. We will focus on making the application fully functional with only two Boolean inputs, this will minimize the number of inputs the user is required to to learn to control the application. But depending on the number of distinct inputs the user are able to produce, the application should try to utilize any extra inputs to improve the user experience. To make typing efficient the user interface should be designed in such a way that the number of separate steps required to type a word is minimized. To further improve efficiency the application should also have predictive features that will try to predict what the user is typing and gives the user a choice to select the predicted text. There are an infinite number of scenarios that can pose a risk to the project's progress, and there will most likely, at the very least, be a number of smaller changes as the project comes along, so when a group member discovers any deviation from the planned schedule, he or her must immediately inform the rest of the project group. The project group will then come together and discuss if there is a need to change the progress plan, or the content of the project. An example of such an situation could be if a group member discovers that an issue takes much more time to implement than planned. Then the group will come together and might conclude that in order to get the project as a whole completed on time, this feature needs to be removed from the project. The feature is then removed from the project, and the progress plan and Gantt diagram is updated in order to reflect the new plan. Another example could be sickness within the project group. This would lead to an increased workload on the remaining members, and similarly as in the previous example, features might have to be either simplified, replaced, or removed in order to meet the deadline of the project. The responsibility for reporting any eventual deviation from the plan to implement a specific issue, falls to the group member that have been assigned to have responsibility for that issue. The project leader is responsible for the project progress plan as a whole.");
            }

            /**
             * Prints the second result used in the project report on layout efficiency
             * @throws IOException
             */
            public void testLayout2() throws IOException {
                LetterLogger letterLogger = setupLogger();

                LayoutTester layoutTester = new LayoutTester(letterLogger.getStats().getOptimalLayouts(), 2, 5, 6);
                layoutTester.testText("Let's welcome the candidates, Governor Bush and Vice President Gore. Good evening, from Wake Chapel at Wake Forest University at Winston-Salem, North Carolina. I'm Jim Lehrer of the News Hour on PBS. Welcome to this second election 2000 debate between the Republican candidate for president, George W. Bush of Texas, and the Democratic candidate, Vice President Al Gore. These debates are sponsored by the Commission on Presidential Debates. The format and the rules are those negotiated by representatives of the two campaigns. Only the subjects tonight and the questions are mine. The format tonight is that of a conversation. The only prevailing rule is that no single response can ever, ever exceed two minutes. The prevailing rule for the audience here in the hall is as always, absolute quiet, please. Good evening, Governor Bush, Vice President Gore. The end of our 90 minutes last week in Boston, the total time each of you took was virtually the same. Let's see if we can do the same tonight, or come close. Governor Bush, the first question goes to you. One of you is about to be elected the leader of the single-most powerful nation in the world, economically, financially, militarily, diplomatically, you name it. Have you formed any guiding principles for exercising this enormous power?\n" +
                        " I have, I have. First question is what's in the best interests of the United States? What's in the best interests of our people? When it comes to foreign policy that will be my guiding question. Is it in our nation's interests? Peace in the Middle East is in our nation's interests. Having a hemisphere that is free for trade and peaceful is in our nation's interests. Strong relations in Europe is in our nation's interest. I've thought a lot about what it means to be the president. I also understand that an administration is not one person, but an administration is dedicated citizens who are called by the president to serve the country, to serve a cause greater than self, and so I've thought about an administration of people who represent all America, but people who understand my compassionate and conservative philosophy. I haven't started naming names except for one person, and that's Mr. Richard Cheney who I thought did a great job the other night. He's a vice presidential nominee who represents -- I think people got to see why I picked him. He's man of solid judgment and he's going to be a person to stand by my side. One of the things I've done in Texas is I've been able to put together a good team of people. I've been able to set clear goals. The goals ought to be an education system that leaves no child behind, Medicare for our seniors, a Social Security system that's safe and secure, foreign policy that's in our nation's interest, and a strong military, and then bring people together to achieve those goals. That's what a Chief Executive Officer does. So I've thought long and hard about the honor of being the President of the United States.\n" +
                        " Vice President Gore?\n" +
                        " Yes, Jim. I've thought a lot about that particular question, and I see our greatest national strength coming from what we stand for in the world. I see it as a question of values. It is a great tribute to our founders that 224 years later this nation is now looked to by the peoples on every other continent and the peoples from every part of this earth as a kind of model for what their future could be. And I don't think that's just the kind of exaggeration that we take pride in as Americans. It's really true, even the ones that sometimes shake their fists at us. As soon as they have a change that allows the people to speak freely, they're wanting to develop some kind of blueprint that will help them be like us more, freedom, free markets, political freedom. So I think first and foremost our power ought to be wielded to in ways that form a more perfect union. The power of example is America's greatest power in the world. And that means, for example, standing up for human rights. It means addressing the problems of injustice and inequity, along the lines of race and ethnicity here at home, because in all these other places around the world where they're having these terrible problems, when they feel hope, it is often because they see in us a reflection of their potential. So we've got to enforce our civil rights laws. We've got to deal with things like racial profiling. And we have to keep our military strong. We have the strongest military, and I'll do whatever is necessary, if I'm president, to make sure that it stays that way. But our real power comes, I think, from our values.\n" +
                        " Should the people of the world look at the United States, Governor, and say, should they fear us, should they welcome our involvement, should they see us as a friend, everybody in the world? How would you project us around the world, as president?\n" +
                        " Well, I think they ought to look at us as a country that understands freedom where it doesn't matter who you are or how you're raised or where you're from, that you can succeed. I don't think they'll look at us with envy. It really depends upon how our nation conducts itself in foreign policy. If we're an arrogant nation, they'll resent us. If we're a humble nation, but strong, they'll welcome us. And it's -- our nation stands alone right now in the world in terms of power, and that's why we have to be humble. And yet project strength in a way that promotes freedom. So I don't think they ought to look at us in any way other than what we are. We're a freedom-loving nation and if we're an arrogant nation they'll view us that way, but if we're a humble nation they'll respect us.\n" +
                        " A humble nation.\n" +
                        " I agree with that. I agree with that. I think that one of the problems that we have faced in the world is that we are so much more powerful than any single nation has been in relationship to the rest of the world than at any time in history, that I know about, anyway. That there is some resentment of U.S. power. So I think that the idea of humility is an important one. But I think that we also have to have a sense of mission in the world. We have to protect our capacity to push forward what America's all about. That means not only military strength and our values, it also means keeping our economy strong. You know, in the last, or two decades ago it was routine for leaders of foreign countries to come over here and say you guys have got to do something about these horrendous deficits because it's causing tremendous problems for the rest of the world, and we were lectured to all the time. The fact that we have the strongest economy in history today is not good enough. We need to do more. But the fact that it is so strong enables us to project the power for good that America can represent.\n" +
                        " Does that give us -- does our wealth, our good economy, our power, bring with it special obligations to the rest of the world?\n" +
                        " Yes, it does. Take, for example, Third World debt. I think we ought to be forgiving Third World debt under certain conditions. I think, for example, if we're convinced that a Third World country that's got a lot of debt would reform itself, that the money wouldn't go into the hands of a few but would go to help people, I think it makes sense for us to use our wealth in that way, or to trade debt for valuable rain forest lands, makes that much sense, yes. We do have an obligation, but we can't be all things to all people. We can help build coalitions but we can't put our troops all around the world. We can lend money but we have to do it wisely. We shouldn't be lending money to corrupt officials. So we have to be guarded in our generosity.\n" +
                        " Let's go through some of the specifics now. New question. Vice President Gore, the governor mentioned the Middle East. Here we're talking at this stage in the game about diplomatic power that we have. What do you think the United States should do right now to resolve that conflict over there?\n" +
                        " The first priority has to be on ending the violence, dampening down the tensions that have arisen there. We need to call upon Syria to release the three Israeli soldiers who have been captured. We need to insist that Arafat send out instructions to halt some of the provocative acts of violence that have been going on. I think that we also have to keep a weather eye toward Saddam Hussein because he is taking advantage of this situation to once again make threats, and he needs to understand that he's not only dealing with Israel, he is dealing -- he's dealing with us if he is making the kind of threats that he's talking about there. The use of diplomacy in this situation has already, well, it goes hour-by-hour and day-by-day now. It's a very tense situation there. But in the last 24 hours there has been some subsiding of the violence there. It's too much to hope that this is going to continue, but I do hope that it will continue. Our country has been very active with regular conversations with the leaders there. And we just have to take it day-to-day right now. But one thing I would say where diplomacy is concerned, Israel should feel absolutely secure about one thing. Our bonds with Israel are larger than agreements or disagreements on some details of diplomatic initiatives. They are historic, they are strong, and they are enduring. And our ability to serve as an honest broker is something that we need to shepherd.\n" +
                        " Governor?");

            }

            /**
             * Tests a string that is provided
             * @param text a text to test
             * @throws IOException
             */
            public void testLayout(String text) throws IOException {
                LetterLogger letterLogger = setupLogger();

                LayoutTester layoutTester = new LayoutTester(letterLogger.getStats().getOptimalLayouts(), 2, 5, 6);
                layoutTester.testText(text);
            }

            /**
             * Prints the adaptive layout code
             * @throws IOException
             */
            public void getAdaptiveLayoutCode() throws IOException {
                LetterLogger letterLogger = setupLogger();

                LayoutTester layoutTester = new LayoutTester(letterLogger.getStats().getOptimalLayouts(), 2, 5, 6);
                layoutTester.testText("Test");
                layoutTester.printAdaptiveLayoutCode();
            }

            /**
             * Prints the data sampled from the wordlist tab indented to show the letter frequencies given a previous
             * letter
             * @throws IOException
             */
            public void getLetterFrequencyStatistic() throws IOException {
                LetterLogger letterLogger = setupLogger();
                letterLogger.getStats().printStatisticsCopyFriendly();

            }

            /**
             * Test of a dictionary variant that only shows words starting with the search
             * @param search What to search for
             */
            public void testDictionary(String search){
                Dictionary dictionary = new Dictionary();
                Dictionary.SortingOrder prefferedOrder = Dictionary.SortingOrder.FREQUENCY_HIGH_TO_LOW;
                dictionary.findSuggestions(search);
                dictionary.printPrimarySuggestions();
            }

            /**
             * Test of a dictionary variant that shows words starting with and containing the search
             * @param search What to search for
             */
            public void testDictionary2(String search){
                Dictionary dictionary = new Dictionary();
                Dictionary.SortingOrder prefferedOrder = Dictionary.SortingOrder.FREQUENCY_HIGH_TO_LOW;
                dictionary.findSuggestions(search);
                dictionary.getSuggestions();
            }

            /**
             * Prints ETAO as symbols as used in backend and android code
             * @throws IOException
             */
            public void printEtao() throws IOException {
                LetterLogger letterLogger = setupLogger();
                letterLogger.getStats().printETAO();
            }

            /**
             * Used for printing the test string into google sheets
             * @param text
             */
            public void printTabInjected(String text){
                StringUtil.StringTabInjection(text);
            }

            /**
             * Used to print the layout data for various purposes.
             * @param forJava Prints code for java
             * @param forCopy Prints the layout strings as simple text strings
             * @param forSpreadsheet Prints the layout data tab indented for each letter
             * @throws IOException
             */
            public void printLayoutData(boolean forJava, boolean forCopy, boolean forSpreadsheet) throws IOException {
                LetterLogger letterLogger = new LetterLogger();
                letterLogger.sampleLetterOccurrence();
                letterLogger.getStats().findOptimalLayouts(forJava,forCopy,forSpreadsheet);
            }
        }

        Research r = new Research();
        //r.getAdaptiveLayoutCode();
        //r.getLetterFrequencyStatistic();
        //r.testLayout1();
        //r.testLayout2();
        //r.testLayout("accessible virtual keyboard");
        //r.testDictionary("lif");
        //r.testDictionary2("lif");
        //r.printEtao();
        //r.printTabInjected("taoiswcbphfmdrelnguvyjkqxz");
        //r.printLayoutData(false,false,true);
    }
}
