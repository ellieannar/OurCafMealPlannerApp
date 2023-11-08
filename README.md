# OurCafMealPlannerApp


<h2>User Interface Project Deliverable 1: Requirements</h2>

<h3>Roles and Responsibilities</h3>

<p>
 
**Leah** - (Lead) Accessibility, Interaction Design, Marketing, Quality Assurance, Technical Development,  User Interface Design, Subject Matter Expertise, (Lead) User Research, Usability Testing

**Ellieanna** - Change Management, Information Architecture, Marketing, Project Management, (Lead) Technical Development, Subject Matter Expertise, User Research, (Lead) Usability Testing

**Maddie** - Business Analysis, Marketing, Metrics Analysis, Technical Development, (Lead) Subject Matter Expertise, User Research, Usability Testing, Enterprise Architecture

**Meilyn** - Accessibility, Content Strategy, (Lead) Marketing, Technical Development, Visual Design, Subject Matter Expertise, User Research, Usability Testing
</p>

<h3>Project Scope</h3>

We will be developing an app that allows Biola students to preview upcoming Caf meals, indicate preferences, and create a schedule based on their meal plan, preferences, and personal schedule. The goal of creating this app is to provide students with information about the meals offered at the Caf so they can plan out their week more effectively and use their meal swipes on food they know they will enjoy. We will generate a recommended plan based on a user's food preferences/allergies, availability, and meal plan. This feature will save their preferences, allowing the user to edit them whenever they choose, and will create a new plan for them each Sunday when their meal plan resets. In addition to planning out meals, they will be able to preview the available food each day and make changes to the created plan. We will not indicate the availability of any food not specified on the Caf website. We will not ensure food allergy considerations are 100% accurate. We will allow users to rate their meals and potentially base future meal selections on these ratings. 


<h3>Project Requirements</h3>

**User Analysis**

We have 3 personas. First we have _Shelby_, representing our ‘average’ person. 

- Housing: On campus

- Year: Junior

- Athlete Status: Not an athlete

- Major: SSTH student (Nursing Major)
  
- Meal plan: Weekly meals & flex
  
- Frequency of caf meals: Eats nearly every day
  
- Planning of caf meals: Goes when she feels like it
  
- Method of previewing meal options: Uses caf website to preview meals
  
- Dietary restrictions: No dietary restrictions
  
- Favorite caf meal: Mexican food
  
- Least favorite caf meal: Seafood
  
Next we have our athlete persona, _Parker_.

 
- Housing: On campus
- Year: Sophomore
- Athlete Status: Athlete
- Major: SSTH student (Computer Science Major)
- Meal plan: Weekly meals & flex
- Frequency of caf meals: Eats nearly every day
- Planning of caf meals: Plans 1-3 days in advance
- Method of previewing meal options: Uses CafWizard to preview meals
- Dietary restrictions: No dairy
- Favorite caf meal: Burrito bar
- Least favorite caf meal: Chicken Alfredo


Finally we have our off-campus persona _Caleb_.

- Housing: Off campus
- Year: Senior
- Athlete Status: Not an athlete
- Major: Bible Major
- Meal plan: None
- Frequency of caf meals: 3-4 meals per week
- Planning of caf meals: Goes when he feels like it
- Method of previewing meal options: Uses CafWizard to preview meals
- Dietary restrictions: No dietary restrictions
- Favorite caf meal: Curry
- Least favorite caf meal: None - not picky

<h2>Contextual Task Analysis</h2>

We conducted a survey of the Biola population regarding the following questions:
- What is your gender?
- What is your academic year?
- Do you live on campus?
- Are you a student athlete?
- What school are you enrolled in?
- What type of meal plan do you have?
- How often do you eat in the caf?
- How far ahead do you plan your caf meals?
- What do you use to check the week’s meals?
- Do you have any dietary restrictions?
- What is your favorite caf meal?
- What is your least favorite caf meal?

This survey was posted in several locations around Biola’s campus as well as sent out via email to all MCS students and texted to our close friends. In total we had 43 responses from people with a wide variety of backgrounds. View our results [here](https://docs.google.com/spreadsheets/d/19pNcCcnBcNP44WnKT5_K3jXJWhsbMuA9OJyEbeiUNF4/edit?usp=sharing) in order to see details such as a user’s current tasks, work patterns, work environments, and conceptual frameworks.

<h2>Usability Goal Setting</h2>

<h3>Usability Goals </h3>

**Learnability**

Test users should be able to accomplish their desired task on our application the first time with no more than 2 questions for developers. Each task (previewing upcoming Caf meals, indicating meal preferences, and inputting pieces of data to contribute to a personalized schedule) should take no more than 10 seconds to figure out how to begin. All cues should have consistency with Android Material Design, consistency within the application, and consistency with comparable real-world objects (such as a Caf menu).

**Memorability**

Returning users should be able to remember how to move to and accomplish each task on the application in no more than 5 seconds upon using it for the second time or after a hiatus of over a week. Tasks should demand user recognition rather than recall unless absolutely necessary through use of visual cues such as drop-down menus, clear iconography, and hints.

**Efficiency**

Regular users (users who use the application on at least a weekly basis) should be able to accomplish tasks in a timely manner. Previewing upcoming Caf meals should take less than 5 seconds; indicating meal preferences should take less than 5 seconds; inputting data for a personalized schedule should take less than a minute for each category of information (food preferences, availability, meal plan) and less than 4 minutes overall.

**Error**

Each session of use on the application should return no more than 2 error messages.

<h3>Satisfaction Goals</h3>

We will quantify the satisfaction goals of our application by giving users a scale of 1 to 5 to rate their happiness after use. If not rated full marks for a given version, the satisfaction score should increase for the next version.

<h2>Platform Capabilities and Constraints </h2>

Our application will be capable of being deployed on Android phones only. It will require internet access. 

<h2>General Design Guidelines</h2>

We will be using Material 3 (Material You). This will include foundations, style, and components. As we create our Lo-Fi and Hi-Fi prototypes we will begin to use Material 3.

<h2>Accessibility Considerations</h2>

- Clear Visuals
- Alternate text
- Contrasting values for color blindness
- Allergies/Dietary Restrictions
- Incorporate specialized meals into schedule
- Clearly indicate ingredients and dietary restriction warnings
- Meal Plan/Commuter Availability
- Account for students’ meal plans and availability on campus in calculating ideal schedule
