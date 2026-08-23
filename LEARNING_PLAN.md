# Spotly — Learning Plan & Progress Tracker

Spotly is a resource/slot reservation backend, built as a **learning project** targeting
the depth expected of a **2-3 YOE backend developer** (Spring Boot / JPA / PostgreSQL / concurrency / security).

Domain model:
```
User -> Reservation -> Slot -> Resource
```
- User = WHO
- Resource = WHAT is being reserved
- Slot = WHEN (a bookable time window on a Resource)
- Reservation = WHO reserved WHAT and WHEN

Working rule with the assistant: no bulk code generation. One small step at a time,
explain the "why" before the "how", write most of the code myself, review before moving on.

---

## How to resume a session

1. Read the **Status snapshot** below first.
2. Read the last 1-2 entries in **Session log**.
3. Pick up at the item marked `<- currently here`.
4. At the end of a sitting, update the snapshot + append a session log entry, then commit
   this file along with the code.

---

## Status snapshot

_Last updated: 2026-08-23_

- **P1 — CRUD hygiene**: in progress
  - [x] `User` entity, repo, service (basic)
  - [x] `Resource` entity, repo, service (basic)
  - [x] `CreateUserRequest` DTO (id removed — mass assignment fix)
  - [x] `CreateResourceRequest` DTO (id removed)
  - [ ] Fix `CreateResourceRequest` validation: `@NotNull` -> `@NotBlank` on `name`; decide `@Length` vs `@Size`
  - [ ] Wire `@Valid` into both POST controller methods
  - [ ] `POST /users` returns `ResponseEntity<User>` with `201` (currently missing entirely — `UserController` only has `GET /users`)
  - [ ] `POST /resources` updated to accept `CreateResourceRequest` instead of raw `Resource`
  - [ ] `GET /users/{id}`, `GET /resources/{id}` (service methods exist, unused)
  - [ ] Fix null-returning service methods (`.orElse(null)`) -> throw `NotFoundException` + `@RestControllerAdvice` mapping to 404
  - [ ] Pagination & sorting on `GET /users`, `GET /resources` (`Pageable`)
  - [ ] OpenAPI/Swagger (`springdoc-openapi`)
- **P2 — JPA relationships**: not started (`Slot`, `Reservation` entities don't exist yet)
- **P3 — Transactions**: not started
- **P4 — Concurrency** (centerpiece): not started
- **P5 — DB fundamentals**: not started (taught opportunistically, not as a block)
- **P6 — Security**: not started
- **P7 — Testing**: not started (to be interleaved per-phase, esp. P4)
- **P8 — Docker (trimmed)**: Postgres-only compose exists; app is not containerized yet
- **P9 — Redis**: optional / last, only if time remains

---

## Full phase plan

### P1 — CRUD hygiene
Goal: boring, correct, fast. Don't linger on annotation trivia.
- DTOs for create requests (no `id` field — prevents mass-assignment/overwrite via `save()`)
- `@Valid` actually wired into controllers (annotations on a DTO do nothing without it)
- Global `@RestControllerAdvice` + consistent error response shape
- Pagination & sorting on list endpoints
- OpenAPI docs

### P2 — JPA relationships
`User <-> Reservation`, `Resource <-> Slot`, `Slot <-> Reservation`.
For each relationship: who owns the FK? which table stores it? then decide
`@ManyToOne` / `@OneToMany` / `mappedBy` / `@JoinColumn`.
Also: lazy vs eager, N+1, `JOIN FETCH` / `@EntityGraph`, DTO projections instead of
returning entities directly.

### P3 — Transactions
Transaction boundary placement (why `@Transactional` goes on the service, not the
controller or repository), propagation (`REQUIRED` / `REQUIRES_NEW`), rollback
behavior, isolation levels — taught against the real reservation-creation flow.

### P4 — Concurrency (the centerpiece — most interview signal per hour spent)
Must be **tested**, not just explained. Each of these gets a concurrent test
(e.g. `ExecutorService` firing parallel requests at the same slot) proving the
invariant "only one reservation succeeds":
1. Race condition / double booking (reproduce it first, unguarded)
2. Lost update
3. Optimistic locking (`@Version`)
4. Pessimistic locking (`PESSIMISTIC_WRITE` / `SELECT ... FOR UPDATE`)
5. Atomic conditional update
6. Deadlocks & lock ordering

### P5 — DB fundamentals (light touch, only where it bites)
Constraints/indexes on the reservation table, `EXPLAIN ANALYZE` on whatever query
actually turns out slow, HikariCP pool sizing. Taught opportunistically.

### P6 — Security
AuthN vs authZ, password hashing (BCrypt), JWT access tokens, roles
(`USER`/`ADMIN`), securing reservation endpoints (only owner or admin can cancel),
401 vs 403. Refresh-token rotation can be simplified if time-constrained.

### P7 — Testing (interleaved above, plus a final pass)
Mockito unit tests on services, `@SpringBootTest` + Testcontainers integration
tests against real Postgres, concurrency test suite from P4 consolidated.

### P8 — Docker (trimmed — no deep internals lecture)
Dockerfile for the Spring app itself (currently only Postgres is containerized),
compose with app+db(+redis), container-to-container networking (service name vs
`localhost`), volumes for persistence, secrets kept out of source control.

### P9 — Redis (optional, only if time remains)
Cache-aside pattern on a read-heavy endpoint, TTL, invalidation on write.

---

## Decisions log

Running record of judgment calls made along the way, so we don't re-litigate them.

- DTOs for create requests never include `id` — prevents client-controlled
  overwrite via `JpaRepository.save()` merge semantics (mass assignment / OWASP
  over-posting).
- Mapping DTO -> entity is done inline in the controller for now (no dedicated
  mapper class) — revisit only if mapping logic grows non-trivial.
- Security (P6) tentatively placed after Concurrency (P4) — concurrency is the
  more unique story for a reservation system; security is still core but more
  generic across projects. Open to swapping order.
- Redis (P9) demoted to optional/last — good LinkedIn talking point, not a
  bar-raiser at 2-3 YOE, so it loses to concurrency/security/testing if time is tight.

---

## Session log

### 2026-08-23
- Reviewed existing repo state: `User`/`Resource` entities, repos, services,
  controllers already existed from a prior session.
- Identified `UserController` was missing `POST /users` despite the service
  supporting it.
- Identified mass-assignment risk: raw entities accepted as `@RequestBody`,
  letting a client set `id` and overwrite existing rows via `save()`.
- Created `CreateUserRequest` and `CreateResourceRequest` DTOs (id removed).
- Found validation inconsistency in `CreateResourceRequest`: `@NotNull` (should
  be `@NotBlank` for strings) and `@Length` (Hibernate-specific; `@Size` is the
  portable Jakarta equivalent) — not yet fixed.
- Reworked the overall phase plan to weight toward 2-3 YOE interview signal:
  trimmed Docker theory, demoted Redis, added pagination/sorting and OpenAPI
  (previously missing), moved testing to be interleaved rather than a final phase.
- Created this file to track progress across sessions.

<!-- Add new entries above this line, newest at the bottom (or reverse -- pick one and stay consistent) -->
