import java.awt.*;
import java.util.*;
import java.util.Random;

public class Common {
	public int studentsNumber;
	public String[] studentsNames = {"Michael","Jackson","Rosalinda","Bryant","Jerome",
								"Maggie","Reuben","Gilbert","Elinor","Ahmad","Korey",
								"Dianna","Lisa","Rosalyn","Darron","Aurelio","Abigail","Khabib",
								"Josie","Mohammed","Jeannine","Barbra","Irwin","Sunny","Connor"};
	public int windowWidth;
	public int windowHeight;
	public UniversityMap map;
	public ArrayList<Academician> academicians;
	public ArrayList<Speaker> speakers;
	public ArrayList<Student> students;
	public ArrayList<Assessment> assessments;

	public Random random;

	public LabFactory labFactory;
	public QuizFactory quizFactory;
	public HomeworkFactory homeworkFactory;

	public boolean graduation;
	public Vector2D graduationPosition;

	public int assessmentTimer;

	public Common(int windowWidth,int windowHeight,int studentsNumber) {
		random = new Random();

		labFactory = new LabFactory(this);
		quizFactory = new QuizFactory(this);
		homeworkFactory = new HomeworkFactory(this);

		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.studentsNumber = studentsNumber;
		this.graduation = false;
		this.graduationPosition = new Vector2D(825,465);
		this.assessmentTimer = 0;

		this.map = new UniversityMap("NU_map",new Vector2D(0,0),new Stationary(),this,"images/NUMap-Faded.jpg");

		ArrayList<Integer> pickedNum = new ArrayList<Integer>(); 
		int randomNameIndex = randomInt(0,studentsNumber);
		this.students = new ArrayList<Student>();
		for (int i = 0; i < studentsNumber; ++ i) {
			while (pickedNum.contains(randomNameIndex))
				randomNameIndex = randomInt(0,studentsNumber);
			pickedNum.add(randomNameIndex);
			this.students.add(new Student(studentsNames[randomNameIndex], new Vector2D(randomInt(0,this.windowWidth-50),randomInt(0,this.windowHeight-50)), new Rest(5), this));

		}

		this.speakers = new ArrayList<Speaker>();
		this.speakers.add(new Speaker("Nazarbayev",new Vector2D(750,450),new Stationary(false,false),this,"images/NursultanNazarbayev.gif"));
		this.speakers.add(new Speaker("Tokayev",new Vector2D(900,450),new Stationary(false,false),this,"images/KassymJomartTokayev.gif"));

		this.academicians = new ArrayList<Academician>();
		this.academicians.add(new Academician("de Nivelle",new Vector2D(randomInt(0,this.windowWidth-50),randomInt(0,this.windowHeight-50)),new Rest(5),this,"images/HansDeNivelle.gif"));
		this.academicians.add(new Academician("Katsu",new Vector2D(randomInt(0,this.windowWidth-50),randomInt(0,this.windowHeight-50)),new Rest(5),this,"images/ShigeoKatsu.gif"));
		this.academicians.add(new Academician("Temizer",new Vector2D(randomInt(0,this.windowWidth-50),randomInt(0,this.windowHeight-50)),new Rest(5),this,"images/SelimTemizer.gif"));
		this.academicians.add(new Academician("Tourassis",new Vector2D(randomInt(0,this.windowWidth-50),randomInt(0,this.windowHeight-50)),new Rest(5),this,"images/VassiliosTourassis.gif"));


		this.assessments = new ArrayList<Assessment>();
		academicians.get(0).createAssessment();
		this.assessments.add(new Quiz("Quiz",new Vector2D(-50,-50),new Stationary(false, false), this, 0));
	}

	public int randomInt(int from, int to) {
		return random.nextInt(to-from+1) + from;
	}


	public void changeState(Entity e, int max) {
			int choice = randomInt(0,max);
			switch (choice) {
			case 0:
				e.state = new Rest(randomInt(40,120));
				break;
			case 1:
				e.state = new GotoXY(randomInt(40,240), new Vector2D(randomInt(0,windowWidth-50),randomInt(0,windowHeight-50)));
				break;
			case 2:
				e.state = new ZigZag(randomInt(40,160),  new Vector2D(randomInt(0,windowWidth-50),randomInt(0,windowHeight-50)),
														new Vector2D(randomInt(0,windowWidth-50),randomInt(0,windowHeight-50)));
				break;
			case 3:
				e.state = new Closest(randomInt(40,120), findClosest(e,null));
				break;
			}
	}

	public Assessment findClosest(Entity student,Assessment current) {
		Iterator<Assessment> assessment_iter = assessments.iterator();
		Assessment current_assessment;
		double minDistance = Double.MAX_VALUE;
		Assessment closestAssessment = null;
		while (assessment_iter.hasNext()) {

			current_assessment = assessment_iter.next();
			if (current_assessment.position.distanceTo(student.position) < minDistance && current_assessment!=current) {
				closestAssessment = current_assessment;
				minDistance = current_assessment.position.distanceTo(student.position);
			}

		}

		return closestAssessment;
	}

	public void stepAllEntities(){
		boolean isGraduation = true;
		Assessment current_assessment;
		Student current_student = null;
		Academician current_academician;
		Iterator<Student> student_iter = students.iterator();
		while (student_iter.hasNext()) {
			current_student = student_iter.next();
			current_student.step();
			if (current_student.grade >= 100) {
				if (current_student.position.distanceTo(graduationPosition) <= 5.00) {
					if (current_student.state.toString() != "Stationary")
						current_student.state = new Stationary();
				}
				else
					current_student.state = new GotoXY(5000,graduationPosition);
			} else {
				isGraduation = false;
				if (current_student.state.isOver) {
					changeState(current_student,3);
				}
			}
		}

		if (isGraduation)
			graduation = true;

		Iterator<Academician> academician_iter = academicians.iterator();
		int offset = 0;
		while (academician_iter.hasNext()) {
			current_academician = academician_iter.next();
			current_academician.step();

			if (graduation) {
				if (current_academician.position.distanceTo(graduationPosition.x-100+75*offset,graduationPosition.y-100) <= 5.00) {
					if (current_academician.state.toString() != "Stationary")
						current_academician.state = new Stationary();
				}
				else
					current_academician.state = new GotoXY(5000,new Vector2D(graduationPosition.x-100+75*offset,graduationPosition.y-100));
			}
			else {
				if (current_academician.state.isOver) {
					changeState(current_academician,2);
				}
			}
			offset++;
		}

		//complete assignment
		if (!graduation){
			Iterator<Assessment> assessment_iter = assessments.iterator();
			while (assessment_iter.hasNext()) {

					current_assessment = assessment_iter.next();
					student_iter = students.iterator();

					while (student_iter.hasNext()) {
						current_student = student_iter.next();
						if (current_assessment.position.distanceTo(current_student.position) <= 25.0 && current_student.grade < 100) {
							current_student.grade += current_assessment.points;
							assessment_iter.remove();
							break;
						}
					}

			}
		} else {
			Iterator<Assessment> assessment_iter = assessments.iterator();
			while (assessment_iter.hasNext()) {

				assessment_iter.next().state.isVisible = false;

			}

		}


		if (!graduation) {
			//create assessments if not rest
			if (assessmentTimer >= 20)
				assessmentTimer = 0;
			assessmentTimer++;

			if (assessmentTimer == 1) {
				academician_iter = academicians.iterator();
				while (academician_iter.hasNext()) {
					current_academician = academician_iter.next();
					if (!current_academician.state.toString().equals("Rest") && !current_academician.state.toString().equals("Stationary"))
						current_academician.createAssessment();

				}
			}

		}
		
		

		if (graduation) {
			speakers.get(0).state.isVisible = true;
			speakers.get(1).state.isVisible = true;
		}


	}

	public void drawAllEntities(Graphics2D g2d){

		map.draw(g2d);
		if (graduation)
			g2d.drawString("NU Graduation Ceremony",(int)graduationPosition.x-70, (int)graduationPosition.y + 70);

		Iterator<Assessment> assessment_iter = assessments.iterator();
		while (assessment_iter.hasNext()) {
			Assessment current_assessment = assessment_iter.next();
			if (current_assessment.state.isVisible)
				current_assessment.draw(g2d);

		}

		Iterator<Student> student_iter = students.iterator();
		while (student_iter.hasNext()) {

			student_iter.next().draw(g2d);

		}

		Iterator<Speaker> speaker_iter = speakers.iterator();
		while (speaker_iter.hasNext()) {
			Speaker current_speaker = speaker_iter.next();
			if (current_speaker.state.isVisible)
				current_speaker.draw(g2d);

		}

		Iterator<Academician> academician_iter = academicians.iterator();
		while (academician_iter.hasNext()) {

			academician_iter.next().draw(g2d);

		}
	}

}