.. _verification:

Verification
============


This feature lets you verify that specific requests have been made to external service endpoints. You can verify the number of repeating requests or different
requests performed in certain order.

The ``verify`` method from ``HoverflyRule`` accepts the same ``RequestMatcherBuilder`` DSL that you use for creating the simulations.

It also accepts a ``VerificationCriteria`` lambda function which is extensible. Here are some examples:

.. code-block:: java

    // Verify exactly one request
    hoverfly.verify(
        service(matches("*.flight.*"))
            .get("/api/bookings")
            .anyQueryParams());

    // Verify exactly two requests
    hoverfly.verify(
        service("api.flight.com")
            .put("/api/bookings/1")
            .anyBody()
            .header("Authorization", "Bearer some-token"), times(2));


There are some useful ``VerificationCriteria`` static factory methods provided out-of-the-box. This will be familiar if you are a `Mockito <http://static.javadoc.io/org.mockito/mockito-core/2.8.47/org/mockito/Mockito.html#verify(T)>`_ user.

.. code-block:: java

    times(1)
    atLeastOnce(),
    atLeast(2),
    atMost(2),
    never()


You can also verify that an external service has never been called:

.. code-block:: java

    hoverfly.verifyNever(service(matches("api.flight.*")));


You can call ``verify`` as many times as you want, but requests are not verified in order by default. Support for verification in order will be added in a future release.


Resetting state
---------------

Verification is backed by a journal which logs all the requests made to Hoverfly. If multiple tests are sharing the same Hoverfly instance,
for example when you are using ``HoverflyRule`` with ``@ClassRule``, verification from one test might interfere with the requests triggered by another test.

In this case, you can reset the journal before each test to ensure a clean state for verifications:

.. code-block:: java

    @Before
    public void setUp() throws Exception {

        hoverfly.resetJournal();

    }
