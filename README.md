# Student Information Management System (BE1)

A Java console application for managing students, faculties, subjects, and academic
records. All data is stored in plain text files — no database required.

## Features

**Student / Subject / Faculty management (CRUD)**
- Add, update, delete, and view students, subjects, faculties, and majors
- Search students by ID or name; search subjects by ID or name
- Sort students by name (A–Z), GPA descending, or GPA ascending
- Course registration with drop, undo, and redo support

**Academic tracking**
- Grade management (add / update / delete / view)
- Weighted GPA calculation per student and semester statistics
- Academic transcript generation
- Passed / remaining credit calculation
- Graduation progress overview

## Academic rules

- GPA is credit-weighted over registered subjects that count toward GPA.
- `COURSERA` subjects add a +1.0 bonus, capped at 10.0.
- `NONE_GPA` subjects (e.g. Internship) do not affect GPA.
- A subject is passed with a final score of **5.0** or higher.
- Graduation requires **145** credits (see `src/util/AcademicPolicy.java`).

## Architecture (MVC)

- `src/model` — entities: `Student`, `Faculty`, `Major`, `Subject` (+ `Normal`,
  `Elective`, `Coursera`, `NoneGpa` subclasses)
- `src/view` — console menus and input validation (all menus extend `BaseMenuView`)
- `src/controller` — menu flow and coordination between views and services
- `src/service` — business logic (`StudentService`, `FacultyService`,
  `SubjectService`, `RegistrationService`, `GradeService`)
- `src/repository` — file persistence for `src/data/*.txt`
- `src/DataStructure` — hand-written structures (see below)
- `src/util` — shared policy constants

## Data structures (hand-implemented)

- `MyLinkedList` — stores each student's registered subjects
- `MyStack` — undo / redo history for course registration
- Sorting uses a hand-written merge sort (`StudentService`); searching uses
  manual linear scans (no built-in sort/search for these paths)

## Data files (`src/data`)

| File | Content |
|---|---|
| `students.txt` | Student ID, name, birth date (`dd/MM/yyyy`), status, faculty, major |
| `major.txt` | `FACULTY\|prefix\|name` and `MAJOR\|id\|name` lines |
| `subjects.txt` | `SUBJECT\|ID\|NAME\|CREDITS\|TYPE` lines |
| `registered.txt` | `StudentID\|SubjectID` registrations |
| `grade.txt` | `StudentID\|SubjectID\|Score` grades |

## How to run

Requires Java 8 or newer.

```bash
javac -d out -sourcepath src src/Main.java
java -cp out Main
```

## Usage notes

- Dates must use day/month/year format: `dd/MM/yyyy` (e.g. `25/12/2006`).
- In numeric menus, enter `-1` to quit; in text inputs, enter `Q` to cancel.
- Data is loaded at startup and saved automatically on exit.
