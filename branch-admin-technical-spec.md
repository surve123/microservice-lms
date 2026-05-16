

# Branch Admin Dashboard — Complete Technical Specification

## 1. Overview / Summary Cards — APIs & Services

---

## 📊 TOTAL API COUNT: 47 APIs across 8 Services

---

## 🏗️ ARCHITECTURE

```
┌─────────────────────────────────────────────────────────────┐
│                    CLIENT (Branch Admin UI)                  │
│                  React / Next.js Dashboard                   │
└──────────────────────────┬──────────────────────────────────┘
                           │ HTTPS
┌──────────────────────────▼──────────────────────────────────┐
│                      API GATEWAY                             │
│              (Auth Middleware + Rate Limiting)               │
└──┬──────┬──────┬──────┬──────┬──────┬──────┬───────────────┘
   │      │      │      │      │      │      │
   ▼      ▼      ▼      ▼      ▼      ▼      ▼
┌────┐ ┌────┐ ┌────┐ ┌────┐ ┌────┐ ┌────┐ ┌────┐ ┌────┐
│ S1 │ │ S2 │ │ S3 │ │ S4 │ │ S5 │ │ S6 │ │ S7 │ │ S8 │
│Dash│ │Stu │ │Inst│ │Cour│ │Att │ │Fee │ │Sche│ │Noti│
│bord│ │dent│ │ruct│ │se  │ │end │ │    │ │dul │ │fy  │
└──┬─┘ └──┬─┘ └──┬─┘ └──┬─┘ └──┬─┘ └──┬─┘ └──┬─┘ └──┬─┘
   └──────┴──────┴──────┴──────┴──────┴──────┴──────┘
                           │
              ┌────────────▼────────────┐
              │      PostgreSQL DB       │
              │   (Primary Database)     │
              └────────────┬────────────┘
                           │
              ┌────────────▼────────────┐
              │        Redis Cache       │
              │  (Summary Cards Cache)   │
              └─────────────────────────┘
```

---

## 🔌 SERVICES & APIs

### S1 — Dashboard Service (6 APIs)
Handles all Summary Card data for the Overview panel.

| # | Method | Endpoint | Description |
|---|--------|----------|-------------|
| 1 | GET | `/api/branch/{branchId}/dashboard/summary` | Get all summary card counts in one call |
| 2 | GET | `/api/branch/{branchId}/dashboard/enrolled-students` | Total enrolled students count |
| 3 | GET | `/api/branch/{branchId}/dashboard/active-courses` | Active courses count |
| 4 | GET | `/api/branch/{branchId}/dashboard/instructors` | Total instructors assigned |
| 5 | GET | `/api/branch/{branchId}/dashboard/pending-approvals` | Pending approvals count |
| 6 | GET | `/api/branch/{branchId}/dashboard/revenue-summary` | Revenue & fee collection summary |

---

### S2 — Student Service (10 APIs)

| # | Method | Endpoint | Description |
|---|--------|----------|-------------|
| 7 | GET | `/api/branch/{branchI d}/students` | List all students |
| 8 | POST | `/api/branch/{branchId}/students` | Add new student |
| 9 | GET | `/api/branch/{branchId}/students/{studentId}` | Get student detail |
| 10 | PUT | `/api/branch/{branchId}/students/{studentId}` | Update student |
| 11 | DELETE | `/api/branch/{branchId}/students/{studentId}` | Deactivate student |
| 12 | POST | `/api/branch/{branchId}/students/{studentId}/enroll` | Enroll student in course/batch |
| 13 | GET | `/api/branch/{branchId}/students/{studentId}/progress` | Student progress report |
| 14 | GET | `/api/branch/{branchId}/students/{studentId}/attendance` | Student attendance |
| 15 | GET | `/api/branch/{branchId}/students/{studentId}/fees` | Student fee status |
| 16 | GET | `/api/branch/{branchId}/students/pending-approvals` | List pending enrollment approvals |

---

### S3 — Instructor Service (7 APIs)

| # | Method | Endpoint | Description |
|---|--------|----------|-------------|
| 17 | GET | `/api/branch/{branchId}/instructors` | List all instructors |
| 18 | POST | `/api/branch/{branchId}/instructors` | Add instructor to branch |
| 19 | GET | `/api/branch/{branchId}/instructors/{instructorId}` | Get instructor detail |
| 20 | PUT | `/api/branch/{branchId}/instructors/{instructorId}` | Update instructor |
| 21 | DELETE | `/api/branch/{branchId}/instructors/{instructorId}` | Remove instructor |
| 22 | GET | `/api/branch/{branchId}/instructors/{instructorId}/schedule` | Instructor schedule |
| 23 | GET | `/api/branch/{branchId}/instructors/{instructorId}/performance` | Instructor performance |

---

### S4 — Course & Batch Service (8 APIs)

| # | Method | Endpoint | Description |
|---|--------|----------|-------------|
| 24 | GET | `/api/branch/{branchId}/courses` | List all courses |
| 25 | GET | `/api/branch/{branchId}/courses/{courseId}` | Course detail |
| 26 | POST | `/api/branch/{branchId}/courses/{courseId}/batches` | Create batch |
| 27 | GET | `/api/branch/{branchId}/courses/{courseId}/batches` | List batches |
| 28 | PUT | `/api/branch/{branchId}/batches/{batchId}` | Update batch |
| 29 | DELETE | `/api/branch/{branchId}/batches/{batchId}` | Delete batch |
| 30 | POST | `/api/branch/{branchId}/batches/{batchId}/assign-instructor` | Assign instructor to batch |
| 31 | GET | `/api/branch/{branchId}/batches/upcoming` | Upcoming batch schedules |

---

### S5 — Attendance Service (5 APIs)

| # | Method | Endpoint | Description |
|---|--------|----------|-------------|
| 32 | POST | `/api/branch/{branchId}/attendance` | Mark attendance |
| 33 | GET | `/api/branch/{branchId}/attendance/batch/{batchId}` | Batch attendance report |
| 34 | GET | `/api/branch/{branchId}/attendance/student/{studentId}` | Student attendance report |
| 35 | GET | `/api/branch/{branchId}/attendance/summary` | Branch attendance summary |
| 36 | PUT | `/api/branch/{branchId}/attendance/{attendanceId}` | Update/correct attendance |

---

### S6 — Fee & Payment Service (6 APIs)

| # | Method | Endpoint | Description |
|---|--------|----------|-------------|
| 37 | GET | `/api/branch/{branchId}/fees` | Fee structure list |
| 38 | GET | `/api/branch/{branchId}/fees/collection` | Fee collection report |
| 39 | POST | `/api/branch/{branchId}/fees/payment` | Record fee payment |
| 40 | GET | `/api/branch/{branchId}/fees/pending` | Pending/overdue fees |
| 41 | POST | `/api/branch/{branchId}/fees/discount` | Apply discount/scholarship |
| 42 | GET | `/api/branch/{branchId}/fees/receipt/{paymentId}` | Generate fee receipt |

---

### S7 — Schedule Service (3 APIs)

| # | Method | Endpoint | Description |
|---|--------|----------|-------------|
| 43 | GET | `/api/branch/{branchId}/schedule` | Branch timetable |
| 44 | PUT | `/api/branch/{branchId}/schedule/{scheduleId}` | Update/reschedule class |
| 45 | POST | `/api/branch/{branchId}/schedule/holiday` | Add holiday/leave |

---

### S8 — Notification Service (2 APIs)

| # | Method | Endpoint | Description |
|---|--------|----------|-------------|
| 46 | POST | `/api/branch/{branchId}/notifications/announce` | Post announcement |
| 47 | POST | `/api/branch/{branchId}/notifications/send` | Send notification to users |

---

## 🗄️ DATABASE SCHEMA

### Table 1: `branches`
```sql
CREATE TABLE branches (
  id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name          VARCHAR(255) NOT NULL,
  address       TEXT,
  phone         VARCHAR(20),
  email         VARCHAR(100),
  working_hours JSONB,
  is_active     BOOLEAN DEFAULT true,
  created_at    TIMESTAMP DEFAULT NOW(),
  updated_at    TIMESTAMP DEFAULT NOW()
);
```

### Table 2: `users` (Students, Instructors, Branch Admin)
```sql
CREATE TABLE users (
  id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  branch_id     UUID REFERENCES branches(id),
  name          VARCHAR(255) NOT NULL,
  email         VARCHAR(100) UNIQUE NOT NULL,
  phone         VARCHAR(20),
  role          ENUM('branch_admin', 'instructor', 'student'),
  password_hash TEXT NOT NULL,
  is_active     BOOLEAN DEFAULT true,
  created_at    TIMESTAMP DEFAULT NOW(),
  updated_at    TIMESTAMP DEFAULT NOW()
);
```

### Table 3: `courses`
```sql
CREATE TABLE courses (
  id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  branch_id     UUID REFERENCES branches(id),
  title         VARCHAR(255) NOT NULL,
  description   TEXT,
  duration_days INT,
  fee_amount    DECIMAL(10,2),
  is_active     BOOLEAN DEFAULT true,
  created_at    TIMESTAMP DEFAULT NOW()
);
```

### Table 4: `batches`
```sql
CREATE TABLE batches (
  id             UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  course_id      UUID REFERENCES courses(id),
  branch_id      UUID REFERENCES branches(id),
  instructor_id  UUID REFERENCES users(id),
  batch_name     VARCHAR(100),
  start_date     DATE,
  end_date       DATE,
  timing         VARCHAR(50),
  capacity       INT,
  status         ENUM('upcoming','active','completed','cancelled'),
  created_at     TIMESTAMP DEFAULT NOW()
);
```

### Table 5: `enrollments`
```sql
CREATE TABLE enrollments (
  id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  student_id    UUID REFERENCES users(id),
  batch_id      UUID REFERENCES batches(id),
  branch_id     UUID REFERENCES branches(id),
  status        ENUM('pending','approved','rejected','completed'),
  enrolled_at   TIMESTAMP DEFAULT NOW(),
  approved_by   UUID REFERENCES users(id),
  approved_at   TIMESTAMP
);
```

### Table 6: `attendance`
```sql
CREATE TABLE attendance (
  id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  student_id    UUID REFERENCES users(id),
  batch_id      UUID REFERENCES batches(id),
  branch_id     UUID REFERENCES branches(id),
  date          DATE NOT NULL,
  status        ENUM('present','absent','late','excused'),
  marked_by     UUID REFERENCES users(id),
  created_at    TIMESTAMP DEFAULT NOW()
);
```

### Table 7: `fee_payments`
```sql
CREATE TABLE fee_payments (
  id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  student_id      UUID REFERENCES users(id),
  enrollment_id   UUID REFERENCES enrollments(id),
  branch_id       UUID REFERENCES branches(id),
  amount_due      DECIMAL(10,2),
  amount_paid     DECIMAL(10,2),
  discount        DECIMAL(10,2) DEFAULT 0,
  payment_date    TIMESTAMP,
  payment_method  ENUM('cash','online','cheque'),
  status          ENUM('pending','paid','overdue','partial'),
  receipt_no      VARCHAR(50) UNIQUE,
  created_at      TIMESTAMP DEFAULT NOW()
);
```

### Table 8: `schedules`
```sql
CREATE TABLE schedules (
  id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  batch_id      UUID REFERENCES batches(id),
  branch_id     UUID REFERENCES branches(id),
  day_of_week   ENUM('Mon','Tue','Wed','Thu','Fri','Sat','Sun'),
  start_time    TIME,
  end_time      TIME,
  room          VARCHAR(50),
  is_holiday    BOOLEAN DEFAULT false,
  created_at    TIMESTAMP DEFAULT NOW()
);
```

### Table 9: `notifications`
```sql
CREATE TABLE notifications (
  id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  branch_id     UUID REFERENCES branches(id),
  sent_by       UUID REFERENCES users(id),
  title         VARCHAR(255),
  message       TEXT,
  target_role   ENUM('all','student','instructor'),
  channel       ENUM('in_app','email','sms'),
  sent_at       TIMESTAMP DEFAULT NOW()
);
```

---

## 🔄 FLOW DIAGRAM

```
Branch Admin Login
       │
       ▼
┌─────────────────────────────────────────────────────┐
│              DASHBOARD (Summary Cards)               │
│  [Students] [Courses] [Instructors] [Approvals]     │
│  [Revenue]  [Upcoming Batches]                      │
└──────┬──────┬──────┬──────┬──────┬──────────────────┘
       │      │      │      │      │
       ▼      ▼      ▼      ▼      ▼
   Students Courses Instruct Attend  Fees
   Mgmt     &Batch  or Mgmt  ance    &Pay
       │      │      │      │      │
       ▼      ▼      ▼      ▼      ▼
  ┌─────────────────────────────────────┐
  │         PostgreSQL Database          │
  │  branches | users | courses          │
  │  batches  | enrollments | attendance │
  │  fee_payments | schedules            │
  └──────────────┬──────────────────────┘
                 │
                 ▼
          ┌─────────────┐
          │ Redis Cache  │
          │ (Dashboard   │
          │  Summary)    │
          └─────────────┘

ENROLLMENT FLOW:
Student Applies → Pending Approval → Branch Admin Reviews
      → Approved ──→ Enrolled in Batch → Fee Generated
      → Rejected ──→ Notification Sent

FEE FLOW:
Enrollment Created → Fee Record Generated → Student Pays
      → Receipt Generated → Revenue Updated in Dashboard

ATTENDANCE FLOW:
Instructor/Admin Marks → Attendance Saved → 
      Reports Available → Alerts if Below Threshold
```

---

## 📋 SUMMARY

| Service | APIs | DB Tables Used |
|---------|------|----------------|
| Dashboard Service | 6 | All tables (aggregated) |
| Student Service | 10 | users, enrollments |
| Instructor Service | 7 | users, batches |
| Course & Batch Service | 8 | courses, batches |
| Attendance Service | 5 | attendance |
| Fee & Payment Service | 6 | fee_payments, enrollments |
| Schedule Service | 3 | schedules, batches |
| Notification Service | 2 | notifications |
| **TOTAL** | **47** | **9 Tables** |

---

